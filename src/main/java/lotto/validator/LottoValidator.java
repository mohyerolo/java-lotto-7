package lotto.validator;

import java.util.List;

public class LottoValidator {
    private static final int LOTTO_MIN_NUM = 1;
    private static final int LOTTO_MAX_NUM = 45;
    private static final int LOTTO_NUM_COUNT = 6;
    private static final String LOTTO_NUM_COUNT_ERROR = "로또 번호는 6개여야 합니다.";
    private static final String LOTTO_NUM_DUPLICATED = "로또 번호는 중복될 수 없습니다.";
    private static final String LOTTO_NUM_RANGE_ERROR = "로또 번호는 " + LOTTO_MIN_NUM + "과 " + LOTTO_MAX_NUM + "사이여야 합니다.";

    public static void validate(List<Integer> numbers) {
        validateNumSize(numbers);
        validateLottoNum(numbers);
        validateDuplicated(numbers);
    }

    private static void validateNumSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUM_COUNT_ERROR);
        }
    }

    private static void validateDuplicated(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUM_DUPLICATED);
        }
    }

    private static void validateLottoNum(List<Integer> numbers) {
        if (numbers.stream().anyMatch(num -> num < LOTTO_MIN_NUM || num > LOTTO_MAX_NUM)) {
            throw new IllegalArgumentException(LOTTO_NUM_RANGE_ERROR);
        }
    }
}