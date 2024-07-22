package inventory;

import java.util.List;

import items.Item;

public interface Inventory<T extends Item> {
	
	public void addItem(T item); // quando o item for coletado, ele é adicionado ao inventário (se tiver espaço)
	public void selectItem(T item);
	public Item findItem(String name); // no caso de um item consumível, selecionar significa consumir ou remover. se for um livro, pode ser lido ou removido.
	public void removeItem(T item); // remover um item do inventário
	List<T> getItems(); // uma forma de obter a lista de itens
	
}
