package games;

/**
 * This class represents a BST Node in the Hunger Games
 * 
 * Contains a District as its data
 * 
 * @author Pranay Roni
 */
public class TreeNode {
    private District district;
    private TreeNode left;
    private TreeNode right;

    /**
     * 
     * Creates TreeNode with a district and left and right children.
     * 
     * @param district of which this TreeNode should contain as its data
     * @param left     will be this TreeNode's left child
     * @param right    will be this TreeNode's right child
     */
    public TreeNode(District district, TreeNode left, TreeNode right) {
        this.district = district;
        this.left = left;
        this.right = right;
    }

    /**
     * Creates a TreeNode with null data and children.
     */
    public TreeNode() {
        this(null, null, null);
    }

    // Getters and setters

    /**
     * Gets district (data) from this node
     * 
     * @return the district corresponding to this node
     */
    public District getDistrict() {
        return district;

    }

    /**
     * Sets this district as provided by client.
     * 
     * @param district will become this TreeNode's new district
     */
    public void setDistrict(District district) {
        this.district = district;
    }

    /**
     * Gets left child of this node
     * 
     * @return left node
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Sets left child of this node
     * 
     * @param node will become this node's new left child
     */
    public void setLeft(TreeNode node) {
        left = node;
    }

    /**
     * Gets right child of this node
     * 
     * @return
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Sets right child of this node
     * 
     * @param node will become this node's new right child
     */
    public void setRight(TreeNode node) {
        right = node;
    }
}
