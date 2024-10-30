package lotto.domain;

import lotto.service.LottoNumGenerator;

import java.util.Comparator;
import java.util.List;

public class Lotto {
    private static final int LOTTO_MIN_NUM = 1;
    private static final int LOTTO_MAX_NUM = 45;
    private static final int LOTTO_NUM_COUNT = 6;
    private static final String LOTTO_NUM_COUNT_ERROR = "로또 번호는 6개여야 합니다.";
    private static final String LOTTO_NUM_DUPLICATED = "로또 번호는 중복될 수 없습니다.";
    private static final String LOTTO_NUM_RANGE_ERROR = "로또 번호는 " + LOTTO_MIN_NUM + "과 " + LOTTO_MAX_NUM + "사이여야 합니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
        sortNums();
    }

    public Lotto(LottoNumGenerator lottoNumGenerator) {
        numbers = generateLottoNumbers(lottoNumGenerator);
        sortNums();
    }

    private List<Integer> generateLottoNumbers(LottoNumGenerator lottoNumGenerator) {
        return lottoNumGenerator.generateNumbers(LOTTO_MIN_NUM, LOTTO_MAX_NUM, LOTTO_NUM_COUNT);
    }

    private void validate(List<Integer> numbers) {
        validateNumSize(numbers);
        validateLottoNum(numbers);
        validateDuplicated(numbers);
    }

    private void validateNumSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUM_COUNT_ERROR);
        }
    }

    private void validateDuplicated(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUM_DUPLICATED);
        }
    }

    private void validateLottoNum(List<Integer> numbers) {
        if (numbers.stream().anyMatch(num -> num < LOTTO_MIN_NUM || num > LOTTO_MAX_NUM)) {
            throw new IllegalArgumentException(LOTTO_NUM_RANGE_ERROR);
        }
    }

    private void sortNums() {
        numbers.sort(Comparator.naturalOrder());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}