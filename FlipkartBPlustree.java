import java.util.ArrayList;
import java.util.Collections;

public class FlipkartBPlusTree {

    private ArrayList<Integer> productList;

    public FlipkartBPlusTree() {
        productList = new ArrayList<>();
    }

    // Insert Product ID
    public void insert(int productID) {
        productList.add(productID);
        Collections.sort(productList);
    }

    // Search Product ID
    public boolean search(int productID) {
        return productList.contains(productID);
    }

    // Display all Product IDs
    public void displayProducts() {
        System.out.println("Product IDs in Sorted Order:");
        for (int id : productList) {
            System.out.print(id + " ");
        }
        System.out.println();
    }

    // Range Search
    public void rangeSearch(int start, int end) {
        System.out.println("\nProducts between " + start + " and " + end + ":");

        for (int id : productList) {
            if (id >= start && id <= end) {
                System.out.print(id + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        FlipkartBPlusTree tree = new FlipkartBPlusTree();

        tree.insert(1001);
        tree.insert(1010);
        tree.insert(1020);
        tree.insert(1035);
        tree.insert(1050);
        tree.insert(1065);
        tree.insert(1080);

        tree.displayProducts();

        int searchID = 1020;

        if (tree.search(searchID)) {
            System.out.println("\nProduct ID " + searchID + " Found");
        } else {
            System.out.println("\nProduct ID " + searchID + " Not Found");
        }

        tree.rangeSearch(1010, 1065);
    }
}