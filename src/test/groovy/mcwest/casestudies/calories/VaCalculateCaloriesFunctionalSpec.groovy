package mcwest.casestudies.calories

import spock.lang.Ignore
import spock.lang.Specification


/**
 * Calorie calculation algorithm.
 */
class VaCalculateCaloriesFunctionalSpec extends Specification {

    @SuppressWarnings("GrEqualsBetweenInconvertibleTypes")
    @Ignore
    def "calculates calories: #comment"() {
        given:
            def carbValue = 1
            def fatValue = 2
            def proteinValue = 3
            def alcoholValue = 4
            Food food = new Food(carb: carbValue, fat: fatValue,
                    protein: proteinValue, alcohol: alcoholValue)

        when:
            // note: calculateCalories method is not implemented until VzCalculateCaloriesFinal
            NutrientValue calories = calculateCalories(food)

        then:
            calories.value ==
                    4 * carbValue +
                    9 * fatValue +
                    4 * proteinValue +
                    7 * alcoholValue
    }

}