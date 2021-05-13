package cn.itcast.travel.domain;

import java.math.BigDecimal;

/**
 * @Author: chong
 * @Date: 2021-03-20
 */

class Test {
    public static void main(String[] args) {
        Test t = new Test();
        BigDecimal random = t.makeRandom(39f, 36f, 1);

    }

    /**
     * 生成指定范围，指定小数位数的随机数
     * @param max 最大值
     * @param min 最小值
     * @param scale 小数位数
     * @return
     */
    BigDecimal makeRandom(float max, float min, int scale){
        BigDecimal cha = new BigDecimal(Math.random() * (max-min) + min);
        return cha.setScale(scale,BigDecimal.ROUND_HALF_UP);//保留 scale 位小数，并四舍五入
    }
}

