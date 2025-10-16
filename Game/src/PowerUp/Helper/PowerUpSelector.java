package PowerUp.Helper;

import PowerUp.Abstractions.BasePowerUp;
import PowerUp.PowerUpWithLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Enum.RarityEnum;
import PowerUp.RarityWithPowerUps;

import java.util.stream.Collectors;

public class PowerUpSelector {
    public static ArrayList<BasePowerUp> selectPowerUps(ArrayList<PowerUpWithLevel> currentPowerUps, int amount) {
        //TODO: refactor
        ArrayList<BasePowerUp> startingList = GlobalPowerUpHelper.getGlobalPowerUpList();
        List<String> forbiddenPowerUps = currentPowerUps.stream().filter(PowerUpWithLevel::isMaxLevel)
                .map(x -> x.getPowerUp().getName())
                .toList();

        int sizeDifference = startingList.size() - forbiddenPowerUps.size();

        if (sizeDifference < 0) {
            throw new RuntimeException("No power ups left to unlock");
        }

        if (sizeDifference > amount) {
            amount = sizeDifference;
        }

        ArrayList<BasePowerUp> selectedPowerUps = new ArrayList<>();
        List<BasePowerUp> availablePowerUps = startingList.stream().filter(x -> forbiddenPowerUps.stream().noneMatch(y -> y.equals(x.getName()))).toList();

        for (int i = 0; i < amount; i++) {
            List<RarityWithPowerUps> powerUpsPerRarity= Arrays.stream(RarityEnum.values())
                    .map(x -> new RarityWithPowerUps(x,
                            availablePowerUps.stream()
                                    .filter(y -> y.getRarityEnum() == x)
                                    .toList()))
                    .toList();
            List<RarityEnum> possibleRarities = powerUpsPerRarity.stream()
                    .filter(x -> !x.isEmpty())
                    .map(RarityWithPowerUps::getRarityEnum)
                    .sorted((x, y) -> (int)Math.floor(x.getWeight() - y.getWeight()))
                    .toList();

            double sum = possibleRarities.stream().mapToDouble(x->x.getWeight()).sum();
            double difference = 100 - sum;

            if (difference > 0) {
                double addition = difference / possibleRarities.size();
                possibleRarities.forEach(x -> x.increaseWeight(addition));
            }

            double randomValue = Math.random() * 100;

            RarityEnum selectedRarity = possibleRarities.stream()
                    .filter(x -> x.getWeight() <= randomValue)
                    .reduce((first, second) -> second)
                    .orElseThrow();

            List<BasePowerUp> possiblePowerUps = powerUpsPerRarity.stream().filter(x -> x.getRarityEnum() == selectedRarity)
                    .findFirst().map(RarityWithPowerUps::getPowerUps).orElseThrow();

            int possiblePowerUpAmount = possiblePowerUps.size();
            int randomIndex = (int)Math.round(Math.random() * possiblePowerUpAmount - 1);
            BasePowerUp selectedPowerUp = possiblePowerUps.get(randomIndex);
            selectedPowerUps.add(selectedPowerUp);
        }

        return selectedPowerUps;

        //TODO: complete rate calculation and dynamic scanning

    }
}
