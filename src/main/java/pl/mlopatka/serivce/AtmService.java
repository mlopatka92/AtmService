package pl.mlopatka.serivce;

import pl.mlopatka.model.Withdraw;

public interface AtmService {

    Withdraw doWithdraw(int totalAmount);
}
