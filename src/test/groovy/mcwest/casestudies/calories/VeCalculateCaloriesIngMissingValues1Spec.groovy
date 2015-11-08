package mcwest.casestudies.calories

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Treat missing ingredient nutrient values as zero and flag.
 * If missing food nutrient values for carb/fat/pro, flag is undefined.
 */
class VeCalculateCaloriesIngMissingValues1Spec extends Specification {

    @Ignore
    // TODO: implement calculateCalories method
    @Unroll
    def "calculates calories, flagging missing ingredient values: #comment"() {
        when:
            NutrientValue calories = calculateCalories(food)

        then:
            calories.value == cal

        where:
            comment            | carb | fat  | pro  | alc  | carbM | fatM  | proM  | alcM  || cal  | calM
            'happy path'       | 1    | 2    | 3    | 4    | false | false | false | false || 62   | false

            'ing missing carb' | 0    | 2    | 3    | 4    | true  | false | false | false || 58   | true
            'ing missing fat'  | 1    | 0    | 3    | 4    | false | true  | false | false || 44   | true
            'ing missing pro'  | 1    | 2    | 0    | 4    | false | false | true  | false || 50   | true
            'ing missing alc'  | 1    | 2    | 3    | 0    | false | false | false | true  || 34   | true

            'zero carb'        | 0    | 2    | 3    | 4    | false | false | false | false || 58   | false
            'zero fat'         | 1    | 0    | 3    | 4    | false | false | false | false || 44   | false
            'zero pro'         | 1    | 2    | 0    | 4    | false | false | false | false || 50   | false
            'zero alc'         | 1    | 2    | 3    | 0    | false | false | false | false || 34   | false

            'missing carb'     | null | 2    | 3    | 4    | null  | false | false | false || null | null
            'missing fat'      | 1    | null | 3    | 4    | false | null  | false | false || null | null
            'missing pro'      | 1    | 2    | null | 4    | false | false | null  | false || null | null
            'missing alc'      | 1    | 2    | 3    | null | false | false | false | null  || 34   | false

            // *M denotes nutrient was calculated from sum of ingredient nutrients where one or more
            // ingredient was missing value for the nutrient
    }

}