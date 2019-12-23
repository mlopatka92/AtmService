package pl.mlopatka.serivce;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.mlopatka.model.Withdraw;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AtmServiceTest {

    private AtmService atmService = new AtmServiceImpl();

    @ParameterizedTest
    @MethodSource("withdrawArguments")
    public void shouldCalculateValidWithdraw(Integer totalMoney, Withdraw expectedWithdraw) {
        //when
        Withdraw actualWithdraw = atmService.doWithdraw(totalMoney);

        //than
        Assertions.assertEquals(expectedWithdraw, actualWithdraw);
    }

    private static Stream<Arguments> withdrawArguments() {
        return Stream.of(
                Arguments.of(890, prepareWithdraw(new Integer[]{200, 4}, new Integer[]{50, 1}, new Integer[]{20, 2})),
                Arguments.of(120, prepareWithdraw(new Integer[]{100, 1}, new Integer[]{20, 1})),
                Arguments.of(1990, prepareWithdraw(new Integer[]{200, 9}, new Integer[]{100, 1}, new Integer[]{50, 1}, new Integer[]{20, 2})),
                Arguments.of(10, prepareWithdraw(new Integer[]{10, 1})),
                Arguments.of(0, prepareWithdraw())
        );
    }

    private static Withdraw prepareWithdraw(Integer[] ... banknoteCounts) {
        if(ArrayUtils.isEmpty(banknoteCounts)) {
            return new Withdraw();
        }

        return new Withdraw(getBanknoteAmountMap(banknoteCounts), "PLN");
    }

    private static Map<Integer, Integer> getBanknoteAmountMap(Integer[] ... banknoteCounts) {
        return Arrays.stream(banknoteCounts).collect(Collectors.toMap(b -> b[0], b -> b[1]));
    }
}