package mcwest.casestudies.calories

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Add missing values cases (if any input nutrients are missing, calories are unknown).
 */
class VcCalculateCaloriesMissingValues2Spec extends Specification {

    @Ignore
    // TODO: implement calculateCalories method
    @Unroll
    def "calculate calories: #comment"() {
        when:
        NutrientValue calories = calculateCalories(food)

        then:
        calories.value == cal

        where:
        comment        | carb | fat  | pro  | alc  || cal
        'happy path'   | 1    | 2    | 3    | 4    || 62

        'missing carb' | null | 2    | 3    | 4    || null
        'missing fat'  | 1    | null | 3    | 4    || null
        'missing pro'  | 1    | 2    | null | 4    || null
        'missing alc'  | 1    | 2    | 3    | null || null
    }

}