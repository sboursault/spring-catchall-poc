package poc.arkham.research.domain.model;

import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyUnitTest {

    BigDecimal ONE_HALF = new BigDecimal("0.5");
    BigDecimal ONE_THIRD = new BigDecimal("0.33333333333333333333");
    BigDecimal TWO_THIRDS = new BigDecimal("0.66666666666666666666");
    Money _1_EUR = Money.parse("EUR 1");
    Money _5_CTS = Money.parse("EUR 0.05");
    BigDecimal EUR_TO_USD_RATE = new BigDecimal("1.24");

    @Test
    public void applyDiscount_inFavorOfSeller() {
        assertThat( applyDiscount( _1_EUR, ONE_HALF   )).isEqualTo( Money.parse("EUR 0.50") );
        assertThat( applyDiscount( _1_EUR, ONE_THIRD  )).isEqualTo( Money.parse("EUR 0.34") );  // and not 0,333...
        assertThat( applyDiscount( _1_EUR, TWO_THIRDS )).isEqualTo( Money.parse("EUR 0.67") );  // and not 0,666...
    }

    private Money applyDiscount(Money price, BigDecimal discount) {
        return price.multipliedBy(discount, RoundingMode.UP);
    }

    @Test
    public void convertCurrency_inFavorOfSeller() {
        assertThat( convertCurrenty( _1_EUR, EUR_TO_USD_RATE )).isEqualTo( Money.parse("EUR 1.24") );
        assertThat( convertCurrenty( _5_CTS, EUR_TO_USD_RATE )).isEqualTo( Money.parse("EUR 0.06") ); // and not 0,062
    }

    private Money convertCurrenty(Money input, BigDecimal rate) {
        return input.multipliedBy(rate, RoundingMode.DOWN);
    }
}
