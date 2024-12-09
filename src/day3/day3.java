package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {
    static int sumMultiplications(ArrayList<String> multiplications) {
        Pattern number = Pattern.compile("[0-9]{1,3}");
        ArrayList<Integer> numbers = new ArrayList<>();
        int sum = 0;

        for (String mul : multiplications) {
            Matcher matcher = number.matcher(mul);
            while(matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }

            sum += numbers.getFirst() * numbers.getLast();

            numbers.clear();
        }

        return sum;
    }

    public static void main(String[] args) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        Pattern muls = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        ArrayList<String> multiplications = new ArrayList<>();
        ArrayList<String> enabledMultiplications = new ArrayList<>();

        try {
            File input = new File("public/day3_input.txt");
            Scanner scanner = new Scanner(input);

            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                stringBuilder.append(line);
                Matcher mulMatcher = muls.matcher(line);
                while(mulMatcher.find()) {
                    multiplications.add(mulMatcher.group());
                }
            }

            String fullInput = stringBuilder.toString();

            /*
                Split by finding substrings that are between "don't()" and "do()" or end of the content.
                Resulting strings are the once where multiplications are enabled.
            */
            for (String substring : fullInput.split("don't\\(\\)(.*?)(do\\(\\)|\\Z)")) {
                Matcher enabledMulMatcher = muls.matcher(substring);

                while(enabledMulMatcher.find()) {
                    enabledMultiplications.add(enabledMulMatcher.group());
                }
            }

//            String test = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
//            String test2 = "mul(315,935)mul(451,658)when()when(353,392)<<select())$,mul(842,863,who()?}<mul(673,921)!$/mul(185,371){select()from()?from()&mul(412,471)' mul(566,75)%+()don't()~[<what()mul(923,512)>why()]*')mul(21,640)from()mul(755,282)>^why()from()mul(442,981)!when()^why()why()(/mul(876,31),who()/[mul(740,627)]<when()mul(616,896)]~){,,:{mul(475,422),%}&'}mul(153,843)+}?@,]&@*?mul(348,553)*$mul(542,601)$)mul(567,799)?}from()how(585,456)from() 'from()?mul(961,457)@;when()]why()<:?(mul(337,246)%where()+ #+how()mul(602,592),]@,^$mul(271,610)select()+why(34,14),//{^$+mul(183,953)who()when(290,111),]>who()/+[mul:+<?when()mul(346,464)*'@from()-where()][&mul(533,382) mul(253,576)!;)#<@from(260,939),mul(903,385)mul(389,764)^%who()why()where()what()mul(445,854)(who()^)?mul(714,731)!}how(100,829)];?where()?do()//<@mul(180,318)/)what(),;]mul(130,856)mul(796,929)mul(564]{how())[~'#>mul(557,285)!(&how()mul(538,865)select()what()~$from()([+when()/mul(826,278):@select(546,967)!?&select()what()mul(847,194)#<who()select()?why())@mul(997,603)]mul$&do()mul(434,51)}how()]^*$mul(18,446)~mul(540,181)]*]?select()when()<[mul(636,536))mulwhy(496,145)^)+>{mul(468,530)*![%where()^<<from(535,872)~mul(276,519)why(120,307):>what()?&mul(277^ mul(780,751)^what()~!#mul(635,606)?*when(): }who()]-don't()<from()&what()#@$mul(491,700)mul(887,685) +#how()}who()+mul(17,441)who(969,103)who()(why()mul(525,434){@]@~mul(760,445)mul(759,803)how()~ *when()mul(584,425)[;why())when(){(why()/how()mul(386,687);*-}]~who(224,978))mul(564,88)!;<-:),@%mul(309where()/;^-*?select() ]from()mul(504,612)*<,@*select(),'@mul(21,237)- when()<>mul(42,308)+,(how()how()$-!~mul(29,791)%)mul(248,71)#when()-)]mul(670when():from(505,247)who()>select()what()'(mul(327,578)from()from(){who()who()from()^/mulwhen(801,176)what()};}<don't(){what()([){]?%-mul(808,60)mul(207,689)mul?-who()~}~where()when()*,mul(81,901)>! :#-:who():/mul(466,327)#~'!),&>mul(776,335where()]why()&]?]:how()how(),do()mul(442,378)when()/^^mul(407,519)'-mul(157,451)+]from()when()when()/mul(689,597#{mul(387,624)><]&$!from()%[-don't()what()why(801,120)how():%select())(mul(847,645)where();&'}#how(698,791)!don't(){)<^mul(694,504)](/*{$who()+mul(480,559)where()#>'who()[}select()!@mul(225,926)when()?%>+mul(631,126)}#@how()mul(712,802)-select()why()~'mul(748,579)from()<who() )don't()how()from()# mul(48,962) @&;!where()/why()$mul(952,98)why()#*where()where()mul(850,784);[$#),;select()mul(420,384)why(),{<%#[mul(667,514)&*'don't(),<mul(401,476)~[how()<mul(441,886)mul(509,477how()~do()when()$?~mul(297,954]when(){from(423,995)@from()(<mul(167,382)&+how(){who():{mul(881,865)who()what():what();mul(335,170)**((mul(158,919)@+~from()'<^ -<mul(497,843)@mul(407,637);*{-#mul(377,786)select()-/,do()mul(63,526) ~why()select()what()mul(499,65)*@-where()!from()/where()mul(830,277)+mul(975,180)@-mul(366,372)(*:}when(623,620);)mul(109,618)-];from()$~what()}+mul(959,532)from();mul(660,115)- mul(48,647)<who()/what()%#{~why()@mul(78,243)where()$from()*+%/&{!mul(798,985)how()(%why()~ [,$!mul(20,376){when(741,432)^,+'who()'?mul#[}from()where()when()[}why()'-mul(905,28)(mul(713,649)<:<mul(357,904)/*/)(]mul(850,489),% *}mul(509,713)mul(321,759){mul(631,140)why()mul(757,485)>{%mul(649,879){:select()~&!,[ !mul(147,693)<+}mul(87,949)-{where(345,734)/ mul(351,115)></who()]$mul(548,692)how()#>mul(52,566)&mul(427,269)}mul(901,457)when()&mul(600,957)mul(734,851)!why()/[$/how();mul(383,59)'why(802,790)(;%'from()who()mul(904how()<;select()@{,who()>[don't()why()}[&[!mul(859,668)what()^mul(483,700)";
//            for (String substring : test2.split("don't\\(\\)(.*?)(do\\(\\)|\\Z)")) {
//                System.out.println(substring);
////                Matcher enabledMulMatcher = muls.matcher(substring);
////
////                while(enabledMulMatcher.find()) {
////                  enabledMultiplications.add(enabledMulMatcher.group());
////                }
//            }

            int mulSum = sumMultiplications(multiplications);
            int enabledMulSum = sumMultiplications(enabledMultiplications);

            System.out.println("Answer for part 1: " + mulSum);
            System.out.println("Answer for part 2: " + enabledMulSum);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file");
        }
    }
}
