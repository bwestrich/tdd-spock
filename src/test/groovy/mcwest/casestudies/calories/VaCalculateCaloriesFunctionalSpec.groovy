package mcwest.casestudies.calories

import spock.lang.Ignore
import spock.lang.Specification


/**
 * Calorie calculation algorithm.
 */
class VaCalculateCaloriesFunctionalSpec extends Specification {

    @Ignore // TODO: implement calculateCalories method
    def "calculate calories: #comment"() {
        given:
        def carbValue = 1
        def fatValue = 2
        def proteinValue = 3
        def alcoholValue = 4
        Food food = new Food(carb: 1, fat: 2, protein: 3, alcohol: 4)

        when:
        NutrientValue calories = calculateCalories(food)

        then:
        calories.value == 4 * carbValue + 9 * fatValue + 4 * proteinValue + 7 * alcoholValue
    }

}