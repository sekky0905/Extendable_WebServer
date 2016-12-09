package jp.co.topgate.sekiguchi.kai.web.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * XSSMeasureTestクラスをテストするためのクラス
 * Created by sekiguchikai on 2016/12/06.
 */
public class XSSMeasureTest {

    @Test
    public void sanitize() {
        String specialCharArray[] = {"&", "<", ">", "\"", "'", "/"};
        String expectedCharArray[] = {"&amp;", "&lt;", "&gt;", "&quot;", "&#x27;", "&#x2F;"};
        for (int i = 0; i < specialCharArray.length; i++) {
            assertThat(XSSMeasure.sanitize(specialCharArray[i]), is(expectedCharArray[i]));

        }
    }


}


