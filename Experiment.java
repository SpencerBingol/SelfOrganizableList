public class Experiment{
    public static void main(String[] args){
        SelfOrganizableList<Character> swaplist = new SelfOrganizableList<Character>();
        SelfOrganizableList<Character> mtflist = new SelfOrganizableList<Character>();
        SelfOrganizableList<Character> aclist = new SelfOrganizableList<Character>();
        
        for(char c = 'E'; c >= 'A'; c--){
            aclist.add(c);
            swaplist.add(c);
            mtflist.add(c);
        }
        
        Character[] ar = {'C', 'A', 'B', 'E', 'C', 'D', 'E', 'D', 'E', 'B', 'D', 'D', 'B', 'B', 'D', 'A', 'D', 'B', 'A', 'B', 'B'};
      
        System.out.println("\n\nArrangement by Move-to-Front Stategy\n" + mtflist);       
        for(Character x : ar){
            System.out.println("searching for: " + x);
            mtflist.searchElementMTF(x);
            System.out.println(mtflist);
        }
        
        
        System.out.println("\n\nArrangement by Swap-toward-Front Stategy\n" + swaplist);       
        for(Character x : ar){
            System.out.println("searching for: " + x);
            swaplist.searchElementSwap(x);
            System.out.println(swaplist);
        }
        
        
        System.out.println("\n\nArrangement by Access-Count Strategy\n" + aclist.display());        
        for(Character x : ar){
            System.out.println("searching for: " + x);
            aclist.searchElementAccessCount(x);
            System.out.println(aclist.display());
        }
    }
}