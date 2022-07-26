import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['../app.component.scss'],
})

export class ProdutosComponent implements OnInit {
  dsProdutos: Produto[] = [];
  dsProdutosTipo: Produto[] = [];
  dsProdutosLucro: Produto[] = [];
  dsProdutoCodigo!: Produto;
  produtoSelecionado: Produto = new Produto();
  produtoCadastro: Produto = new Produto();

  codigoProduto: string = '';
  opcaoSelecionada: string = '1';
  tipoProdutoSelecionado: string = 'Eletrônico';

  msgModal: string = '';
  mostrarTabela: boolean = false;
  mostrarModal: boolean = true;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.buscarProdutos();
  }

  buscarProdutos() {
    this.http.get<Produto[]>('http://localhost:8080/api/v1/produto').subscribe({
      next: (data: Produto[]) => {
        this.dsProdutos = data;
      },
      error: (error) => {
        this.msgModal = error.error.message;
        this.abrirModal('modal-alerta');
      },
    });
  }

  buscarProdutosPorTipo() {
    this.http
      .get<Produto[]>(
        `http://localhost:8080/api/v1/produto/tipo/${this.tipoProdutoSelecionado}`
      )
      .subscribe({
        next: (data: Produto[]) => {
          this.dsProdutosTipo = data;
        },
        error: (error) => {
          this.msgModal = error.error.message;
          this.abrirModal('modal-alerta');
        },
      });
  }

  buscarProdutosPorLucro() {
    this.http
      .get<Produto[]>(`http://localhost:8080/api/v1/produto/lucro`)
      .subscribe({
        next: (data: Produto[]) => {
          this.dsProdutosLucro = data;
        },
        error: (error) => {
          this.msgModal = error.error.message;
          this.abrirModal('modal-alerta');
        },
      });
  }

  buscarProdutoPorCodigo() {
    this.opcaoSelecionada = '4';
    this.mostrarTabela = false;

    this.http
      .get<any>(`http://localhost:8080/api/v1/produto/${this.codigoProduto}`)
      .subscribe({
        next: (data) => {
          if (data.length > 0 || data.length !== undefined) {
            this.opcaoSelecionada = '1';
            this.buscarProdutos();
          }
          this.dsProdutoCodigo = data;
          this.mostrarTabela = true;
        },
        error: (error) => {
          this.msgModal = error.error.message;
          this.abrirModal('modal-alerta');
        },
      });
  }

  selecionarItem(item: any) {
    this.opcaoSelecionada = item.target.value;

    if (this.opcaoSelecionada === '1') {
      this.buscarProdutos();
    } else if (this.opcaoSelecionada === '2') {
      this.buscarProdutosPorTipo();
    } else if (this.opcaoSelecionada === '3') {
      this.buscarProdutosPorLucro();
    } else {
      this.buscarProdutoPorCodigo();
    }
  }

  selecionarTipoProduto(item: any) {
    this.tipoProdutoSelecionado = item.target.value;
    this.buscarProdutosPorTipo();
  }

  selecionarCodigo(item: any) {
    this.codigoProduto = item.target.value;
  }

  selecionarProduto(item: Produto) {
    this.produtoSelecionado = item;
  }

  cadastrar() {
    this.abrirModal('modal-cadastrar');
  }

  cadastrarProduto() {
    this.fecharModal('modal-cadastrar');

    this.http
      .post<Produto>('http://localhost:8080/api/v1/produto', this.produtoCadastro)
      .subscribe({
        next: () => {
          this.msgModal = 'Produto cadastrado com sucesso!';
          this.abrirModal('modal-alerta');
        },
        error: (error) => {
          this.msgModal = error.error.message;
          this.abrirModal('modal-alerta');
        },
      });
  }

  alterar() {
    let radio = <HTMLInputElement>(
      document.querySelector('input[name="nmRadio"]:checked')
    );
    if (!radio || !this.produtoSelecionado) {
      this.msgModal = 'Selecione um produto para alterar!';
      this.abrirModal('modal-alerta');
    } else {
      this.abrirModal('modal-alterar');
    }
  }

  alterarProduto() {
    let produtoAlterado = new Produto();
    produtoAlterado.codigo = this.produtoSelecionado.codigo;
    produtoAlterado.descricao = this.produtoSelecionado.descricao;
    produtoAlterado.tipoProduto = this.produtoSelecionado.tipoProduto;
    produtoAlterado.valorFornecedor = this.produtoSelecionado.valorFornecedor;

    this.fecharModal('modal-alterar');

    this.http
      .put<any>('http://localhost:8080/api/v1/produto', produtoAlterado)
      .subscribe({
        next: () => {
          this.msgModal = 'Produto alterado com sucesso.';
          this.abrirModal('modal-alerta');
        },
        error: (error) => {
          this.msgModal = error.error.message;
          this.abrirModal('modal-alerta');
        },
      });
  }

  deletar() {
    let radio = <HTMLInputElement>(
      document.querySelector('input[name="nmRadio"]:checked')
    );
    if (!radio || !this.produtoSelecionado) {
      this.msgModal = 'Selecione um produto para remover.';
      this.abrirModal('modal-alerta');
    } else {
      this.abrirModal('modal-deletar');
    }
  }

  deletarProduto() {
    let codigo: number = this.produtoSelecionado.codigo;

    this.fecharModal('modal-deletar');

    this.http
      .delete<string>(`http://localhost:8080/api/v1/produto/${codigo}`)
      .subscribe({
        next: () => {
          this.msgModal = 'Produto removido com sucesso.';
          this.abrirModal('modal-alerta');
        },
        error: (error) => {
          this.msgModal = error.error.message;
          this.abrirModal('modal-alerta');
        },
      });
  }

  abrirModal(modal: string) {
    document.getElementById(modal)?.classList.add('is-active');
  }

  fecharModal(modal: string) {
    document.getElementById(modal)?.classList.remove('is-active');
    this.buscarProdutos();
  }
}

export class Produto {
  codigo!: number;
  descricao!: string;
  tipoProduto!: string;
  valorFornecedor?: number;
  quantidadeEstoque!: number;
  quantidadeSaida?: number;
  totalLucro?: number;
}