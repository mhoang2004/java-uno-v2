public class LogicGame {
    // is new color card in cards
    static  public int  isNewColor(Card newCard, User player)
    {
        if(newCard.getColor() ==null)
        {
            for (int i=0; i< player.sizeCards(); i++) {
                if(player.getCard().get(i).getColor() == null)
                    return i;
            }
            return 0;
        }
        for (int i=0; i< player.sizeCards(); i++) 
        {
            if(player.getCard().get(i).getColor() == null)
            {
                continue;
            }
            if(player.getCard().get(i).getColor().charAt(0)== newCard.getColor().charAt(0))
                return i;
        }
            
        return -1;
    }
    // count card in cards to color
    static public int countCard(String color, User player)
    {
        int count =0;
        if(color == null)
        {
            for (Card card : player.getCard()) {
                if(card.getColor()== null)
                    count ++;
            }
        }else{
            for (Card card : player.getCard()) {
                if(card.getColor() == null)
                {
                    continue;
                }
                if(card.getColor().equals(color))
                    count ++;
            }
        }
        
        return count;
    }

}
