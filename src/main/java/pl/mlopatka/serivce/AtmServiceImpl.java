package pl.mlopatka.serivce;

import pl.mlopatka.config.BankConfig;
import pl.mlopatka.model.Withdraw;

import java.util.HashMap;
import java.util.Map;

public class AtmServiceImpl implements AtmService {

    @Override
    public Withdraw doWithdraw(int totalMoney) {
        int moneyLeft = totalMoney;
        Map<Integer, Integer> banknoteAmounts = new HashMap<>();

        for (Integer banknoteDenomination : BankConfig.ACCEPTED_BANKNOTES) {
            if(moneyLeft <= 0) {
                break;
            }

            int banknoteAmount = moneyLeft / banknoteDenomination;

            if (banknoteAmount <= 0) {
                continue;
            }

            moneyLeft -= banknoteAmount * banknoteDenomination;
            banknoteAmounts.put(banknoteDenomination, banknoteAmount);
        }

        return new Withdraw(banknoteAmounts, "PLN");
    }
}
