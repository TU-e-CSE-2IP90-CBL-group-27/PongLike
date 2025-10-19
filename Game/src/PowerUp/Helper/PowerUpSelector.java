package src.PowerUp.Helper;

import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.PowerUpWithLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.Enum.RarityEnum;
import src.PowerUp.RarityWithPowerUps;

import java.util.stream.Collectors;

public class PowerUpSelector {
    private static List<BasePowerUp> getAvailablePowerUps(ArrayList<PowerUpWithLevel> currentPowerUps) {
        ArrayList<BasePowerUp> startingList = GlobalPowerUpHelper.getGlobalPowerUpList();
        List<String> forbiddenPowerUps = currentPowerUps.stream().filter(PowerUpWithLevel::isMaxLevel)
                .map(x -> x.getPowerUp().getName())
                .toList();

        List<BasePowerUp> availablePowerUps = startingList.stream()
                .filter(x -> forbiddenPowerUps.stream()
                        .noneMatch(y -> y.equals(x.getName())))
                .toList();

        return availablePowerUps;
    }

    private static RarityEnum selectPowerUpRarity(List<RarityWithPowerUps> powerUpsPerRarity) {
        System.out.println(powerUpsPerRarity.size() + " given size");
        List<RarityEnum> possibleRarities = powerUpsPerRarity.stream()
                .filter(x -> !x.isEmpty())
                .map(RarityWithPowerUps::getRarityEnum)
                .sorted((x, y) -> (int)Math.floor(x.getWeight() - y.getWeight()))
                .toList();

        System.out.println(possibleRarities.size() + " resulting size");
        fixWeights(possibleRarities);

        return rarityRoll(possibleRarities);
    }

    private static RarityEnum rarityRoll(List<RarityEnum> possibleRarities) {
        double randomValue = Math.random() * 100;
        System.out.println(randomValue);
        RarityEnum selectedRarity = possibleRarities.stream()
                .filter(x -> x.getWeight() >= randomValue)
                .reduce((first, second) -> second)
                .orElseThrow();

        return selectedRarity;
    }

    private static BasePowerUp selectPowerUp(List<RarityWithPowerUps> powerUpsPerRarity, RarityEnum selectedRarity) {
        List<BasePowerUp> possiblePowerUps = powerUpsPerRarity.stream().filter(x -> x.getRarityEnum() == selectedRarity)
                .findFirst().map(RarityWithPowerUps::getPowerUps).orElseThrow();

        int possiblePowerUpAmount = possiblePowerUps.size();
        int randomIndex = (int) Math.round(Math.random() * (possiblePowerUpAmount - 1));

        return possiblePowerUps.get(randomIndex);
    }

    private static void fixWeights(List<RarityEnum> possibleRarities) {
        double sum = possibleRarities.stream().mapToDouble(x-> 100 - x.getWeight()).sum();
        double difference = 60 - sum;
        System.out.println("difference " + difference);

        if (difference > 0) {
            double addition = difference / possibleRarities.size();
            possibleRarities.forEach(x -> x.increaseWeight(addition));
        }
        possibleRarities.forEach(x->System.out.println("weight " + x.getWeight()));

    }

    private static void powerUpSelection(ArrayList<BasePowerUp> availablePowerUps, ArrayList<BasePowerUp> selectedPowerUps) {
        List<RarityWithPowerUps> powerUpsPerRarity= Arrays.stream(RarityEnum.values())
                .map(x -> new RarityWithPowerUps(x,
                        availablePowerUps.stream()
                                .filter(y -> x.equals(y.getRarityEnum()))
                                .toList()))
                .toList();
        powerUpsPerRarity.forEach(x -> System.out.println(x.getRarityEnum() + " " + x.getPowerUps().size()));
        RarityEnum selectedRarity = selectPowerUpRarity(powerUpsPerRarity);
        BasePowerUp selectedPowerUp = selectPowerUp(powerUpsPerRarity, selectedRarity);
        selectedPowerUps.add(selectedPowerUp);
        availablePowerUps.remove(selectedPowerUp);
    }
    public static ArrayList<BasePowerUp> selectPowerUps(ArrayList<PowerUpWithLevel> currentPowerUps, int amount) {
        //TODO: handle exceptions
        ArrayList<BasePowerUp> availablePowerUps = new ArrayList<>(getAvailablePowerUps(currentPowerUps));
        int availablePowerUpAmount = availablePowerUps.size();
        System.out.println("available " + availablePowerUps.size());

        if (availablePowerUpAmount < 1) {
            throw new RuntimeException("No power ups left to unlock");
        }

        if (availablePowerUpAmount > amount) {
            amount = availablePowerUpAmount;
        }

        ArrayList<BasePowerUp> selectedPowerUps = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            powerUpSelection(availablePowerUps, selectedPowerUps);
        }
        setDefaultRarities();
        return selectedPowerUps;

        //TODO: complete rate calculation and dynamic scanning
    }

    private static void setDefaultRarities() {
        RarityEnum.COMMON.setWeight(60);
        RarityEnum.RARE.setWeight(90);
        RarityEnum.LEGENDARY.setWeight(98.5f);
        RarityEnum.INSANE.setWeight(100.01f);
    }
}
