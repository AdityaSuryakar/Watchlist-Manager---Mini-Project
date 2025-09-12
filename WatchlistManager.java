public class WatchlistManager {
    private List<WatchlistItem> watchlist;

    public WatchlistManager() {
        watchlist = new ArrayList<>();
    }

    public void addItem(WatchlistItem item) {
        watchlist.add(item);
    }

    public void removeItem(int id) {
        watchlist.removeIf(item -> item.getId() == id);
    }

    public List<WatchlistItem> viewItems() {
        return watchlist;
    }
}