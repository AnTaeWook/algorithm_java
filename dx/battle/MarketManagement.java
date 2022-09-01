package dx.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

class ProductStructure implements Comparable<ProductStructure>{
    int bId;
    int pId;
    int price;
    int quantity;

    public ProductStructure(int bId, int pId, int price, int quantity) {
        this.bId = bId;
        this.pId = pId;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int compareTo(ProductStructure p) {
        if (price == p.price && bId == p.bId) {
            return quantity - p.quantity;
        }
        if (price == p.price) {
            return bId - p.bId;
        }
        return price - p.price;
    }
}

class SellStructure {
    int sId;
    int pId;
    int price;
    int quantity;
    ArrayList<ProductStructure> productList;

    public SellStructure(int sId, int pId, int price, int quantity) {
        this.sId = sId;
        this.pId = pId;
        this.price = price;
        this.quantity = quantity;
        productList = new ArrayList<>();
    }
}

public class MarketManagement {
    HashMap<Integer, TreeSet<ProductStructure>> listPerProduct;
    HashMap<Integer, ProductStructure> productReceipt;
    HashMap<Integer, SellStructure> sellReceipt;
    HashMap<Integer, Integer> stockPerProduct;

    public void init() {
        listPerProduct = new HashMap<>();
        productReceipt = new HashMap<>();
        sellReceipt = new HashMap<>();
        stockPerProduct = new HashMap<>();
    }

    public int buy(int bId, int mProduct, int mPrice, int mQuantity) {
        stockPerProduct.put(mProduct, stockPerProduct.get(mProduct) == null ? mQuantity : stockPerProduct.get(mProduct) + mQuantity);
        productReceipt.put(bId, new ProductStructure(bId, mProduct, mPrice, mQuantity));
        if (!listPerProduct.containsKey(mProduct)) {
            listPerProduct.put(mProduct, new TreeSet<>());
        }
        listPerProduct.get(mProduct).add(new ProductStructure(bId, mProduct, mPrice, mQuantity));
        return stockPerProduct.get(mProduct);
    }

    public int cancel(int bId) {
        ProductStructure productStructure = productReceipt.get(bId);
        if (productStructure == null || !listPerProduct.get(productStructure.pId).contains(productStructure)) {
            return -1;
        }
        stockPerProduct.put(productStructure.pId, stockPerProduct.get(productStructure.pId) - productStructure.quantity);
        int stock = stockPerProduct.get(productStructure.pId);
        listPerProduct.get(productStructure.pId).remove(productStructure);
        productReceipt.remove(bId);
        return stock;
    }

    public int sell(int sId, int mProduct, int mPrice, int mQuantity) {
        if (stockPerProduct.get(mProduct) == null || stockPerProduct.get(mProduct) < mQuantity) {
            return -1;
        }
        stockPerProduct.put(mProduct, stockPerProduct.get(mProduct) - mQuantity);
        int margin = 0;
        ArrayList<ProductStructure> deleted = new ArrayList<>();
        SellStructure sellStructure = new SellStructure(sId, mProduct, mPrice, mQuantity);

        for (ProductStructure productStructure : listPerProduct.get(mProduct)) {
            if (productStructure.quantity <= mQuantity) {
                deleted.add(productStructure);
                sellStructure.productList.add(productStructure);
                margin += (mPrice - productStructure.price) * productStructure.quantity;
                mQuantity -= productStructure.quantity;
            } else {
                sellStructure.productList.add(new ProductStructure(productStructure.bId, productStructure.pId, productStructure.price, mQuantity));
                listPerProduct.get(mProduct).remove(productStructure);
                listPerProduct.get(mProduct).add(new ProductStructure(productStructure.bId, productStructure.pId, productStructure.price,
                        productStructure.quantity -  mQuantity));
                margin += (mPrice - productStructure.price) * mQuantity;
                mQuantity -= mQuantity;
            }
            if (mQuantity <= 0) {
                break;
            }
        }

        sellReceipt.put(sId, sellStructure);
        for (ProductStructure productStructure : deleted) {
            listPerProduct.get(mProduct).remove(productStructure);
        }
        return margin;
    }

    public int refund(int sId) {
        SellStructure sellStructure = sellReceipt.get(sId);
        if (sellStructure == null) {
            return -1;
        }
        stockPerProduct.put(sellStructure.pId, stockPerProduct.get(sellStructure.pId) + sellStructure.quantity);

        for (int i = 0; i < sellStructure.productList.size(); i++) {
            if (i == sellStructure.productList.size() - 1) {
                ProductStructure refunded = sellStructure.productList.get(i);
                ProductStructure higherOne = listPerProduct.get(refunded.pId).higher(new ProductStructure(refunded.bId - 1, 0, refunded.price, 0));
                if (higherOne != null && refunded.bId == higherOne.bId) {
                    listPerProduct.get(refunded.pId).add(new ProductStructure(refunded.bId, refunded.pId, refunded.price, higherOne.quantity + refunded.quantity));
                    listPerProduct.get(refunded.pId).remove(higherOne);
                    break;
                }
            }
            listPerProduct.get(sellStructure.pId).add(sellStructure.productList.get(i));
        }
        sellReceipt.remove(sId);
        return sellStructure.quantity;
    }
}
