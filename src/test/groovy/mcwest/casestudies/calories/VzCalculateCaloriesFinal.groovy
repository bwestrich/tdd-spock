package mcwest.casestudies.calories

class VzCalculateCaloriesFinal {

    NutrientValue calculateCalories(Food food) {
        int carbFactor = 4
        int fatFactor = 9
        int proFactor = 4
        int alcFactor = 7
        NutrientValue calories = new NutrientValue()
        Double calsFromCarb = food.carb.value != null ? food.carb.value * carbFactor : null
        Double calsFromFat = food.fat.value != null ? food.fat.value * fatFactor : null
        Double calsFromPro = food.protein.value != null ? food.protein.value * proFactor : null
        Double calsFromAlc = food.alcohol.value != null ? food.alcohol.value * alcFactor : 0
        if (calsFromCarb != null && calsFromFat != null && calsFromPro != null) {
            calories.value =
                    new Double(calsFromCarb + calsFromFat + calsFromPro + calsFromAlc)
            calories.missingValues =
                    food.carb.missingValues ||
                    food.fat.missingValues ||
                    food.protein.missingValues
        }
        return calories
    }
}