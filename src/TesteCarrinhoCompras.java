import static org.junit.Assert.*;

import org.junit.Test;


public class TesteCarrinhoCompras {

	@Test
	public void totalCarrinho() {
		CarrinhodeCompra c = new CarrinhodeCompra();
		c.adicionaProduto(new Produto("tenis",100));
		c.adicionaProduto(new Produto("camiseta",50));
		c.adicionaProduto(new Produto("bermuda",70));
		assertEquals(220, c.total());
	}

	@Test
	public void escutaAdicaoDeProduto(){
		CarrinhodeCompra c = new CarrinhodeCompra();
		MockObservadorCarrinho mock = new MockObservadorCarrinho();
		c.adicionarObservador(mock);
		c.adicionaProduto(new Produto("tenis",100));
		mock.verificaRecebimentoProduto("tenis",100);
	}
	
	@Test
	public void adicionarDoisObservadores(){
		CarrinhodeCompra c = new CarrinhodeCompra();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		MockObservadorCarrinho mock2 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);
		c.adicionaProduto(new Produto("tenis",100));
		mock1.verificaRecebimentoProduto("tenis",100);
		mock2.verificaRecebimentoProduto("tenis",100);
	}
	
	@Test
	public void continuaNotificandoComErroEmObservador(){
		CarrinhodeCompra c = new CarrinhodeCompra();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		ObservadorCarrinho mock2 = new MockObservadorComProblema();
		//outra forma de criar: mock2.queroQueVoceDePau();
		MockObservadorCarrinho mock3 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);
		c.adicionarObservador(mock3);
		c.adicionaProduto(new Produto("tenis",100));
		mock1.verificaRecebimentoProduto("tenis",100);
		mock3.verificaRecebimentoProduto("tenis",100);
	}
}
