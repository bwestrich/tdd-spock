package mcwest.casestudies.calories

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Convert to where table (in prep for adding missing value special case).
 */
class VcCalculateCaloriesMissingValues1Spec extends Specification {

    @Ignore
    // TODO: implement calculateCalories method
    @Unroll
    def "calculates calories: #comment"() {
        when:
            NutrientValue calories = calculateCalories(food)

        then:
            calories.value == cal

        where:
            comment      | carb | fat | pro | alc || cal
            'happy path' | 1    | 2   | 3   | 4   || 62
    }

}