

import java.util.*;

public class FeedAnalyser {
    
    private HashMap<String, ArrayList<FeedItem>> recordDict = new HashMap<>();
    private ArrayList<FeedItem> recordList = new ArrayList<>();
    private ArrayList<FeedItem> recordHeapList = new ArrayList<>();
    private int NLARGE = 0;

    public FeedAnalyser(String filename) {
        
        Iterator<FeedItem> iter = new Util.FileIterator(filename);
        while (iter.hasNext()) {
            FeedItem item = iter.next();
            
            this.recordList.add(item);
            if (! this.recordDict.containsKey(item.getUsername())) {
                ArrayList<FeedItem> feedList = new ArrayList<>();
                feedList.add(item);
                this.recordDict.put(item.getUsername(), feedList);
            } else {
                this.recordDict.get(item.getUsername()).add(item);
            }
        }
        
        for (String key: this.recordDict.keySet()) {
            ArrayList<FeedItem> tempList = this.recordDict.get(key);
            Collections.sort(tempList, new Comparator<FeedItem>() {
                @Override
                public int compare(FeedItem o1, FeedItem o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }
        
        this.recordHeapList = this.recordList;
        new Heap().heapSort(this.recordHeapList);
        this.NLARGE = this.recordList.size() - 1;
    }

    /**
     * Return all feed items posted by the given username between startDate and endDate (inclusive)
     * If startDate is null, items from the beginning of the history should be included
     * If endDate is null, items until the end of the history should be included
     * The resulting list should be ordered by the date of each FeedItem
     * If no items that meet the criteria can be found, the empty list should be returned
     *
     * @param username the user to search the posts of
     * @param startDate the date to start searching from
     * @param endDate the date to stop searching at
     * @return a list of FeedItems made by username between startDate and endDate
     *
     * @require username != null
     * @ensure result != null
     */
    public List<FeedItem> getPostsBetweenDates(String username, Date startDate, Date endDate) {
        
        ArrayList<FeedItem> temp = this.recordDict.get(username);
        ArrayList<FeedItem> res = new ArrayList<>();
        
        int startIndex = 0;
        int endIndex = temp.size() - 1;
                
        if (startDate != null) {
            startIndex = this.findIndex(temp, startDate) + 1;
        }
        
        if (endDate != null) {
            endIndex = this.findIndex(temp, endDate);
        }
        
        if (startIndex > endIndex) {
            return res;
        }
        
        for (int i = startIndex; i <= endIndex; i++) {
            res.add(temp.get(i));
        }
        return res;
    }
    
    public int findIndex(ArrayList<FeedItem> list, Date date) {
        int low = 0;
        int high = list.size() - 1;
        int middle = (low + high) / 2;
        
        while(low <= high){
            middle = (low + high) / 2;
            if (list.get(middle).getDate().compareTo(date) > 0) {
                high = middle - 1;
            } else if(list.get(middle).getDate().compareTo(date) < 0){
                low = middle + 1;
            }
        }
        
        if (list.get(middle).getDate().compareTo(date) >= 0) {
            middle -= 1;
        }
        
        return middle;
    }

    /**
     * Return the first feed item posted by the given username at or after searchDate
     * That is, the feed item closest to searchDate that is greater than or equal to searchDate
     * If no items that meet the criteria can be found, null should be returned
     *
     * @param username the user to search the posts of
     * @param searchDate the date to start searching from
     * @return the first FeedItem made by username at or after searchDate
     *
     * @require username != null && searchDate != null
     */
    public FeedItem getPostAfterDate(String username, Date searchDate) {
        
        // get value list by username
        ArrayList<FeedItem> temp = this.recordDict.get(username);
        
        // binary search by time: find search index
        int searchIndex = this.findIndex(temp, searchDate) + 1;
        
        // makeup result and return
        if (searchIndex == temp.size()) {
            return null;
        } else {
            return temp.get(searchIndex);
        }
    }

    /**
     * Return the feed item with the highest upvote
     * Subsequent calls should return the next highest item
     *     i.e. the nth call to this method should return the item with the nth highest upvote
     * Posts with equal upvote counts can be returned in any order
     *
     * @return the feed item with the nth highest upvote value,
     *      where n is the number of calls to this method
     * @throws NoSuchElementException if all items in the feed have already been returned
     *      by this method
     */
    public FeedItem getHighestUpvote() throws NoSuchElementException {
        return this.recordHeapList.get(this.NLARGE -- );
    }
    
    
    /**
     * Return all feed items containing the specific pattern in the content field
     * Case should not be ignored, eg. the pattern "hi" should not be matched in the text "Hi there"
     * The resulting list should be ordered by FeedItem ID
     * If the pattern cannot be matched in any content fields the empty list should be returned
     *
     * @param pattern the substring pattern to search for
     * @return all feed items containing the pattern string
     *
     * @require pattern != null && pattern.length() > 0
     * @ensure result != null
     */
    public List<FeedItem> getPostsWithText(String pattern) {
        
        ArrayList<FeedItem> fList = new ArrayList<>();
        
        for (FeedItem fi: this.recordList) {
            String content = fi.getContent();
            if(textExist(content, pattern)) {
                fList.add(fi);
            }
        }
        Collections.sort(fList, new Comparator<FeedItem>() {
                @Override
                public int compare(FeedItem o1, FeedItem o2) {
                    if (o1.getId() < o2.getId()) {
                        return -1;
                    } else if (o1.getId() > o2.getId()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        return fList;
    }
    
    // if content have text substring
    public boolean textExist(String content, String text) {
        int n = content.length();
        int m = text.length();
        
        Map<Character, Integer> last = new HashMap<>();
        for (int i=0; i<n; i++) {
            last.put(content.charAt(i), -1);
        }
        for (int k=0; k<m; k++) {
            last.put(text.charAt(k), k);
        }
        int i = m - 1;
        int k = m - 1;
        while (i < n) {
            if (content.charAt(i) == text.charAt(k)) {
                if (k == 0) {
                    return true;
                }
                i--;
                k--;
            } else {
                i += m - Math.min(k, 1 + last.get(content.charAt(i)));
                k = m - 1;
            }
        }
        return false;
    }
    
    private class Heap{
        
	public void heapSort(ArrayList<FeedItem> fList){
            int n = fList.size();
            for(int i = n/2 ; i >= 0; i--){
                adjustHeap(fList, i, n);
            }
            for(int j = n-1; j >= 0; j--){
                swap(fList, 0, j);
                adjustHeap(fList, 0, j);
            }
	}
        
	public void adjustHeap(ArrayList<FeedItem> fList, int s, int n){
            for(int i=s; i<n; ){
                int max = i;
                if((2*i+1)<n && fList.get(2*i+1).getUpvotes() > fList.get(max).getUpvotes()) {
                    max = 2*i+1;
                }
                if((2*i+2)<n && fList.get(2*i+2).getUpvotes() > fList.get(max).getUpvotes()) {
                    max = 2*i+2;
                }
                if( max != i ){
                    swap(fList,i,max);   
                    i = max;
                } else {
                    break;
                }
            }
	}
        
	public void swap(ArrayList<FeedItem> fList, int i, int j){
            FeedItem temp = fList.get(i);
            fList.set(i, fList.get(j));
            fList.set(j, temp);
	}
    }
}
