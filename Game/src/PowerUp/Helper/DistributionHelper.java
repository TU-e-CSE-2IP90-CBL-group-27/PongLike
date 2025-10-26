package src.PowerUp.Helper;

import src.Enum.RarityEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributionHelper {
    public static RarityEnum rarityRoll(List<RarityEnum> rarities) {
        double weight = Math.random() * 100;
        System.out.println("Roll is: " + weight);

        for (RarityEnum rarity : rarities) {
            float potentialRarityWeight = rarity.getWeight();
            System.out.println(rarity.toString() + " " + potentialRarityWeight);

            if (potentialRarityWeight > weight) {
                return rarity;
            }

            weight -= potentialRarityWeight;
        }

        return rarities.getLast();
    }

    public static void fixRarityWeights(ArrayList<RarityEnum> rarities) {
        rarities.sort((a, b) -> (int)(a.getWeight() - b.getWeight()));

        double[] weights = rarities.stream().mapToDouble(RarityEnum::getWeight).toArray();
        double initialSum = Arrays.stream(weights).sum();
        double difference = 100 - initialSum;

        if (difference <= 0) {
            return;
        }

        double minimumValue = Arrays.stream(weights).findFirst().orElse(100);
        System.out.println("Minumum value " + minimumValue);
        double[] ratios = Arrays.stream(weights)
                .map(x -> x / minimumValue )
                .toArray();

        double ratioSum = Arrays.stream(ratios).sum();
        System.out.println("Ratio sum" + ratioSum);
        double baseChance = 100 / ratioSum;
        System.out.println(baseChance);

        for (int i = 0; i < weights.length; i++) {
            System.out.println("Base " + baseChance + " ratio " + ratios[i]);
            rarities.get(i).setWeight((float)(baseChance * ratios[i]));
        }
    }

    public static void setDefaultRarityWeights() {
        RarityEnum.COMMON.setWeight(60);
        RarityEnum.RARE.setWeight(30);
        RarityEnum.LEGENDARY.setWeight(8.5f);
        RarityEnum.INSANE.setWeight(1.5f);
    }
}
