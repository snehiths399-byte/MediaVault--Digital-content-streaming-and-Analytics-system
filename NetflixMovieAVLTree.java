class MovieNode {
    String title;
    int height;
    MovieNode left, right;

    MovieNode(String title) {
        this.title = title;
        this.height = 1;
    }
}

public class NetflixMovieAVLTree {

    MovieNode root;

    int height(MovieNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(MovieNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    MovieNode rightRotate(MovieNode y) {
        MovieNode x = y.left;
        MovieNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    MovieNode leftRotate(MovieNode x) {
        MovieNode y = x.right;
        MovieNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    MovieNode insert(MovieNode node, String title) {

        if (node == null)
            return new MovieNode(title);

        if (title.compareToIgnoreCase(node.title) < 0)
            node.left = insert(node.left, title);
        else if (title.compareToIgnoreCase(node.title) > 0)
            node.right = insert(node.right, title);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 &&
            title.compareToIgnoreCase(node.left.title) < 0)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 &&
            title.compareToIgnoreCase(node.right.title) > 0)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 &&
            title.compareToIgnoreCase(node.left.title) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 &&
            title.compareToIgnoreCase(node.right.title) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    boolean search(MovieNode node, String title) {
        if (node == null)
            return false;

        if (title.equalsIgnoreCase(node.title))
            return true;

        if (title.compareToIgnoreCase(node.title) < 0)
            return search(node.left, title);

        return search(node.right, title);
    }

    void inorder(MovieNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.title);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        NetflixMovieAVLTree tree = new NetflixMovieAVLTree();

        tree.root = tree.insert(tree.root, "Inception");
        tree.root = tree.insert(tree.root, "Avatar");
        tree.root = tree.insert(tree.root, "Titanic");
        tree.root = tree.insert(tree.root, "Joker");
        tree.root = tree.insert(tree.root, "Interstellar");
        tree.root = tree.insert(tree.root, "The Dark Knight");
        tree.root = tree.insert(tree.root, "Avengers");

        System.out.println("Movies in Sorted Order:");
        tree.inorder(tree.root);

        String movie = "Joker";

        if (tree.search(tree.root, movie))
            System.out.println("\nMovie Found: " + movie);
        else
            System.out.println("\nMovie Not Found");

        System.out.println("\nRoot Movie: " + tree.root.title);
        System.out.println("Tree Height: " + tree.root.height);
        System.out.println("Root Balance Factor: "
                + tree.getBalance(tree.root));
    }
}