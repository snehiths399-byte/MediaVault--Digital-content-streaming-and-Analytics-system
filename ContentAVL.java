class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int key) {
        this.key = key;
        height = 1;
    }
}

public class ContentAVL {

    AVLNode root;

    int height(AVLNode n) {
        return n == null ? 0 : n.height;
    }

    int getBalance(AVLNode n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    AVLNode rightRotate(AVLNode y) {

        System.out.println("Right Rotation at Content ID " + y.key);

        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {

        System.out.println("Left Rotation at Content ID " + x.key);

        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, int key) {

        if (node == null) {
            System.out.println("Insert " + key + " -> No Rotation");
            return new AVLNode(key);
        }

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        node.height = 1 + Math.max(height(node.left),
                                   height(node.right));

        int balance = getBalance(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    boolean search(AVLNode node, int key) {

        if (node == null)
            return false;

        if (node.key == key)
            return true;

        if (key < node.key)
            return search(node.left, key);

        return search(node.right, key);
    }

    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    void printTree(AVLNode root, int space) {

        if (root == null)
            return;

        space += 8;

        printTree(root.right, space);

        System.out.println();

        for (int i = 8; i < space; i++)
            System.out.print(" ");

        System.out.println(root.key);

        printTree(root.left, space);
    }

    public static void main(String[] args) {

        ContentAVL tree = new ContentAVL();

        int contentIds[] =
        {201,202,203,204,205,206,207,208};

        System.out.println(
        "========================================");

        System.out.println(
        "MediaVault - AVL Content Indexing");

        System.out.println(
        "========================================\n");

        for(int id : contentIds)
            tree.root = tree.insert(tree.root, id);

        System.out.println(
        "\n========================================");

        System.out.println(
        "FINAL AVL TREE");

        System.out.println(
        "========================================");

        tree.printTree(tree.root,0);

        System.out.println(
        "\nInorder Traversal:");

        tree.inorder(tree.root);

        System.out.println(
        "\n\nSearch Content ID 205 : "
        + (tree.search(tree.root,205)
        ? "Content Found"
        : "Content Not Found"));

        System.out.println(
        "Search Content ID 250 : "
        + (tree.search(tree.root,250)
        ? "Content Found"
        : "Content Not Found"));

        System.out.println(
        "\n========================================");

        System.out.println(
        "Content Indexing Completed");

        System.out.println(
        "========================================");
    }
}