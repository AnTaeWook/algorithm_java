package dx.week13;

import java.util.Scanner;

class Ingredient {
    int cost;
    int value;

    public Ingredient(int cost, int value) {
        this.cost = cost;
        this.value = value;
    }
}

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];
        Ingredient[] ingredients;

        for (int i = 0; i < testCaseCount; i++) {
            int ingredientCount = scanner.nextInt();
            int targetAmount = scanner.nextInt() - ingredientCount;
            ingredients = new Ingredient[ingredientCount];
            int totalCost = 0;

            for (int j = 0; j < ingredientCount; j++) {
                int cost = scanner.nextInt();
                totalCost += cost;
                ingredients[j] = new Ingredient(j + 1, totalCost);
            }
            if (targetAmount <= 0) {
                answers[i] = totalCost;
                continue;
            }

            Ingredient minimum = new Ingredient(1, 1000000001);
            for (Ingredient ingredient : ingredients) {
                if (minimum.cost * ingredient.value < ingredient.cost * minimum.value) {
                }
            }

        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
