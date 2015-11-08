package mcwest.casestudies.calories

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Don't flag missing ingredient alcohol values.
 */
class VzCalculateCaloriesFinalSpec extends Specification {

    @Unroll
    def "calculates calories, flagging missing ingredient non-alcohol values: #comment"() {
        given:
            NutrientValue carbVal = new NutrientValue(nutrient: Nutrient.CARB, value: carb, missingValues: carbM)
            NutrientValue fatVal = new NutrientValue(nutrient: Nutrient.FAT, value: fat, missingValues: fatM)
            NutrientValue proVal = new NutrientValue(nutrient: Nutrient.PROTEIN, value: pro, missingValues: proM)
            NutrientValue alcVal = new NutrientValue(nutrient: Nutrient.ALCOHOL, value: alc, missingValues: alcM)
            Food food = new Food(carb: carbVal, fat: fatVal, protein: proVal, alcohol: alcVal)
            VzCalculateCaloriesFinal calculator = new VzCalculateCaloriesFinal()

        when:
            NutrientValue calories = calculator.calculateCalories(food)

        then:
            calories.value == cal
            calories.missingValues == calM

            // note: IntelliJ currently doesn't format this table correctly if the double bars are included
            // JetBrains looking into resolving.
        where:
            comment            | carb | fat  | pro  | alc  | carbM | fatM  | proM  | alcM  || cal  | calM
            'happy path'       | 1    | 2    | 3    | 4    | false | false | false | false || 62   | false

            'ing missing carb' | 0    | 2    | 3    | 4    | true  | false | false | false || 58   | true
            'ing missing fat'  | 1    | 0    | 3    | 4    | false | true  | false | false || 44   | true
            'ing missing pro'  | 1    | 2    | 0    | 4    | false | false | true  | false || 50   | true
            'ing missing alc'  | 1    | 2    | 3    | 0    | false | false | false | true  || 34   | false

            'zero carb'        | 0    | 2    | 3    | 4    | false | false | false | false || 58   | false
            'zero fat'         | 1    | 0    | 3    | 4    | false | false | false | false || 44   | false
            'zero pro'         | 1    | 2    | 0    | 4    | false | false | false | false || 50   | false
            'zero alc'         | 1    | 2    | 3    | 0    | false | false | false | false || 34   | false

            'missing carb'     | null | 2    | 3    | 4    | null  | false | false | false || null | null
            'missing fat'      | 1    | null | 3    | 4    | false | null  | false | false || null | null
            'missing pro'      | 1    | 2    | null | 4    | false | false | null  | false || null | null
            'missing alc'      | 1    | 2    | 3    | null | false | false | false | null  || 34   | false

            // *M denotes nutrient is calculated from summing ingredients that have one or more missing nutrient value
    }

}