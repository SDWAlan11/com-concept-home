package com.concept.test.profiling.memory;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BlackJack {

    public static String arrayChallenge(String[] strArray){

        Supplier<IntStream> cardValueStream = () -> Arrays.stream(strArray)
                .mapToInt(string -> Cards.valueOf(string.toUpperCase()).value);


        int maxCard = cardValueStream.get().max().getAsInt();
        int count = cardValueStream.get().sum();

        if(21 > count){
            if(cardValueStream.get().anyMatch(card -> Cards.ACE.value == card) && cardValueStream.get().count() < 3)
                return "blackjack " + Cards.ACE.label;
            else
                return "below " + Cards.getCard(maxCard).label;
        }if(21 == count)
            return "blackjack " + Cards.getCard(maxCard).label;
        else{
            return "above " + Cards.getCard(maxCard).label;
        }
    }

    public static void main(String[] args){
        System.out.println(arrayChallenge(new String[] {"four", "ace", "ten"}));
        System.out.println(arrayChallenge(new String[] {"four", "king", "ten"}));
        System.out.println(arrayChallenge(new String[] {"queen", "ace"}));
    }

    static enum Cards{
        ACE(1,"ace",11),
        TWO(2,"two",2),
        THREE(3,"three",3),
        FOUR(4,"four",4),
        FIVE(5,"five",5),
        SIX(6,"six",6),
        SEVEN(7,"seven",7),
        EIGHT(8,"eight",8),
        NINE(9,"nine",9),
        TEN(10,"ten",10),
        JACK(11,"jack",11),
        QUEEN(12,"queen",12),
        KING(13,"king",13);

        int value;
        String label;
        int realValue;

        Cards(int value, String label, int realValue) {
            this.value = value;
            this.label = label;
            this.realValue = realValue;
        }

        public static Cards getCard(int value){
            for (Cards type : Cards.values()) {
                if (type.realValue == value)
                    return type;
            }

            return null;
        }
    }

}
