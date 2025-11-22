package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity 
@Table(name = "itens_venda")
public class ItemVenda implements Serializable {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id; 
    
    @ManyToOne 
    @JoinColumn(name = "item_produto_fk", nullable = false)
    private Produto produto;
    
    @Column(name = "item_quantidade")
    private int quantidade;
    
    @Column(name = "item_preco_unitario", columnDefinition = "numeric(10,2)")
    private double precoUnitario; 
    
    @ManyToOne 
    @JoinColumn(name = "venda_id_fk", nullable = false)
    private Venda venda; 

    public ItemVenda() {
    }
    
    public ItemVenda(Produto produto, int quantidade, double precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public double calcularSubTotal() {
        return this.quantidade * this.precoUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}