package lotto.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.Model.VO.LottoData;

public class LottoPlayer {

        private Integer currentAmount, spentAmount, earnedAmount;
        private ArrayList<Lotto> ownedLottos;
        public LottoPlayer(Integer insertData){
            CacheValidator.validateCache(insertData);
            this.ownedLottos = new ArrayList<>();
            this.currentAmount=insertData;
            this.spentAmount=0;
            this.earnedAmount=0;
        }

        public Integer orderLottos(){
            Integer purchaseQuantity = currentAmount/1000;
            payMoney(currentAmount);
            return purchaseQuantity;
        }

        public void receiveLotto(Lotto lotto){
            ownedLottos.add(lotto);
        }

        private void payMoney(Integer cache){
            if( cache <= currentAmount ) {
                currentAmount -= cache;
                spentAmount += cache;
            }
        }

        public ArrayList<LottoData> getLottoDatas(){
            ArrayList<LottoData> playerLottoDatas = ownedLottos.stream()
                    .map(Lotto::getData)
                    .collect(Collectors.toCollection(ArrayList::new));
            return playerLottoDatas;
        }

        public ScoreBoard calculateScore(LottoData answerLotto,Integer bonusNumber){
            ScoreBoard roundScore = new ScoreBoard();
            List<Integer> answerNumbers = answerLotto.getNumbers();
            for (Lotto lotto : ownedLottos) {
                Prize prize = lotto.calculatePrize(answerNumbers,bonusNumber);
                roundScore.recordScore(prize);
            }
            return roundScore;
        }

        public boolean remainLotto(){
            return ownedLottos.isEmpty() == false;
        }


}
