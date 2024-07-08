package inventory;

public interface PlayerInventory<T> {
	
	public void addItem(T item); // quando o item for coletado, ele é adicionado ao inventário (se tiver espaço)
	public void selectItem(T item); // no caso de um item consumível, selecionar significa consumir. tbm pode servir para ler informações do item
	public void removeItem(T item); // remover um item do inventário
	
}
