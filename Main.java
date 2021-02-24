import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
//        MyTree myTree = new MyTree();
//        myTree.add(5);
//        myTree.add(4);
//        myTree.add(6);
//        myTree.add(3);
//        System.out.printf("%1$05d%n,   %1$05d%n, %1$05d%n ", Arrays.asList(new int[]{123, 58, 46}));
        NullVertex<Integer> nullVertex = new NullVertex<>();
        Vertex<Integer> vertex = new Vertex<>();
        ArrayDeque<Vertex<Integer>> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(nullVertex);
//        System.out.println(nullVertex instanceof NullVertex);
        MyTree<Integer> myTree = new MyTree<>();
//        myTree.add(2);
//        myTree.add(0);
//        myTree.add(3);
//        myTree.add(5);
//        myTree.add(1);
//        myTree.add(6);
//        myTree.add(4);
        myTree.add(3);
        myTree.add(1);
        myTree.add(5);
        myTree.add(0);
        myTree.add(2);
        myTree.add(4);
        myTree.add(6);
        myTree.show();
        Vertex<Integer> v = new Vertex<>(2);

//        System.out.println();
//        System.out.println(myTree.getCount());
//        System.out.println(myTree.getDepth());
    }
}

class MyTree<T> {
    private Vertex<T> root;
    private int count;
    private int depth;

    public void add(T data) {
        count++;
        if (root == null) {
            root = new Vertex<T>(data);
            return;
        } else
            addTo(data, root);
//        if ()
//        Vertex vertexPointer = root;
//        Vertex prevVertex = new Vertex();
//        boolean toLeft = true;
//        while (vertexPointer != null) {
//            prevVertex = vertexPointer;
//            if (data < vertexPointer.getData()) {
//                vertexPointer = vertexPointer.getLeft();
//                toLeft = true;
//            } else {
//                vertexPointer = vertexPointer.getRight();
//                toLeft = false;
//            }
//        }
//        if (toLeft) {
//            prevVertex.setLeft(new Vertex(null, null, data));
//        } else {
//            prevVertex.setRight(new Vertex(null, null, data));
//        }
//        count++;
//        depth = (int) Math.ceil(
//                Math.log((double) count) / Math.log((double) 2)
//        );
//        System.out.println(depth);
    }

    private void addTo(T data, Vertex<T> vertex) {
        if (vertex.isLesser(data)) {
            if (vertex.getLeft() == null) {
                vertex.setLeft(new Vertex<T>(data));
            } else {
                addTo(data, vertex.getLeft());
            }
        } else {
            if (vertex.getRight() == null) {
                vertex.setRight(new Vertex<T>(data));
            } else {
                addTo(data, vertex.getRight());
            }
        }
    }

    public boolean remove(T data) {
        Vertex<T> current, parent = new Vertex<>();
        current = findTargetAndParent(data, root, root);
        // case when current()
        if (current.getRight() == null) {
            // if target is the root
            if (parent.equals(root))
                parent.setLeft(current.getLeft());
            else { // case when current (target) has only one its child
                if (parent.isLesser(data)) {// removing from the left part
                    // if parent parent is less child  (target) then set child's left leaf at the right parent leaf
                    parent.setRight(current.getLeft());
                } else {// removing from right part
                    // on other case (equals or is greater), set left leaf at the left parent leaf
                    parent.setLeft(current.getLeft());
                }
            }
            // if current has a child that hasn't a left child
        } else if (current.getRight().getLeft() == null) {
            //set left current child at the left leaf of current child
            current.getRight().setLeft(current.getLeft());
            if (parent.equals(root))
                parent.setLeft(current.getRight());
            else {
                if (parent.isLesser(data)) {
                    parent.setRight(current.getRight());
                } else {
                    parent.setLeft(current.getRight());
                }
            }
        } else {
            Vertex<T> leftMost = current.getRight().getLeft();
            Vertex<T> leftMostParent = current.getRight();
            while (leftMost.getLeft() != null) {
                leftMostParent = leftMost;
                leftMost = leftMost.getLeft();
            }
            leftMost.setLeft(current.getLeft());
            leftMost.setRight(current.getRight());
            if (parent.equals(root))
                parent.setLeft(current.getLeft());
            else {
                if (parent.isLesser(current.getData())) {
                    parent.setRight(leftMost);
                } else {
                    parent.setLeft(leftMost);
                }
            }

        }

        return true;
    }

    private Vertex<T> findTargetAndParent(T data, Vertex<T> current, Vertex<T> parent) {
        if (current == null)
            return null;
        if (!current.equals(data)) {
            parent = current;
            if (current.isLesser(data)) {
                current = findTargetAndParent(data, current.getLeft(), parent);
            } else {
                current = findTargetAndParent(data, current.getRight(), parent);
            }
        }
        return current;

    }

    public MyTree() {
    }

    public void show() {
        String data = "%1$05d";//7 symbols
        String leftBranch = "/";// 1sym
        String rightBranch = "\\";
        String nullSign = "*";
        ArrayDeque<T> dequeOfData = new ArrayDeque<>();
        BFS(dequeOfData);
        StringBuilder stringBuilder = new StringBuilder("");
        int depth = (int) Math.ceil(Math.log(count) / Math.log(2));
        T oneData;
        for (int i = 0; i < depth; i++) {
            addSymbol(stringBuilder, " ", (int) Math.pow(2.0, depth - i * 1.0));
            for (int j = 0; j < (int) Math.pow(2.0, i * 1.0) && !dequeOfData.isEmpty(); j++) {
                oneData = dequeOfData.removeLast();
                addSymbol(stringBuilder,
//                        oneData == null ? nullSign :
                        oneData.toString(),
                        1);
                addSymbol(stringBuilder, " ", (int) Math.pow(2.0, depth - i + 1.0) - 1);
            }
//            System.out.println(String.format(stringBuilder.toString(), dataArray));
            System.out.println(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
    }

    private void addSymbol(StringBuilder stringBuilder, String symbol, int count) {
        for (int i = 0; i < count; i++) {
            stringBuilder.append(symbol);
        }
    }

    private void BFS(ArrayDeque<T> forShowing) {
        ArrayDeque<Vertex<T>> deque = new ArrayDeque<>();
        deque.addFirst(root);
        forShowing.addFirst(root.getData());
        Vertex<T> current = new Vertex<>();
        T forNullValue = null;
        while (!deque.isEmpty()) {
            current = deque.removeLast();
            if (current.getLeft() != null) {
                deque.addFirst(current.getLeft());
                forShowing.addFirst(current.getLeft().getData());
            } else {
//                forShowing.addFirst(forNullValue);
            }
            if (current.getRight() != null) {
                deque.addFirst(current.getRight());
                forShowing.addFirst(current.getRight().getData());
            } else {
//                forShowing.addFirst(forNullValue);
            }

        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Vertex getRoot() {
        return root;
    }

    public void setRoot(Vertex root) {
        this.root = root;
    }


}

class Vertex<T> {
    private Vertex<T> left;
    private Vertex<T> right;
    private T data;

    public Vertex() {
    }

    public boolean isLesser(T data) {
        return (Integer) this.data > (Integer) data;
    }


    @Override
    public boolean equals(Object obj) {
        return this.data == obj;
    }

    public Vertex(T data) {
        this.data = data;
    }

    public Vertex<T> getLeft() {
        return left;
    }

    public void setLeft(Vertex<T> left) {
        this.left = left;
    }

    public Vertex<T> getRight() {
        return right;
    }

    public void setRight(Vertex<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

class NullVertex<T> extends Vertex<T>{

}

