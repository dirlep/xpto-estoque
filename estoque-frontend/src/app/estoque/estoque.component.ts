import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Produto } from '../produtos/produtos.component';

@Component({
  selector: 'app-estoque',
  templateUrl: './estoque.component.html',
  styleUrls: ['../app.component.scss']
})

export class EstoqueComponent implements OnInit {

  dsProdutos!: Produto[];
  produtoSelecionado!: Produto;
  request!: MovimentoEstoque;
  msgModal!: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.resetarCampos();
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
  
  cadastrarMovimento() {
    this.http
    .post<MovimentoEstoque>('http://localhost:8080/api/v1/estoque', this.request)
    .subscribe({
      next: () => {
        this.msgModal = 'Movimento cadastrado com sucesso!';
        this.abrirModal('modal-alerta');
        this.resetarCampos();
      },
      error: (error) => {
        this.msgModal = error.error.message;
        this.abrirModal('modal-alerta');
      },
    });
  }

  setTipoMovimento(event: any) {
    this.request.tipoMovimentacao = event.target.value;
  }

  setProduto(event: any) {
    this.request.codigoProduto = event.target.value;
  }

  setDataMovimentacao(event: any) {
    this.request.dataMovimentacao = event.target.value;
  }

  setQuantidadeMovimentada(event: any) {
    this.request.quantidadeMovimentada = event.target.value;
  }

  setValorVenda(event: any) {
    this.request.valorVenda = event.target.value;
  }

  resetarCampos() {
    this.produtoSelecionado = new Produto();
    this.request = new MovimentoEstoque();
  }

  abrirModal(modal: string) {
    document.getElementById(modal)?.classList.add('is-active');
  }

  fecharModal(modal: string) {
    document.getElementById(modal)?.classList.remove('is-active');
  }
}

export class MovimentoEstoque {
  codigoProduto!: number;
  tipoMovimentacao!: string;
  valorVenda?: number;
  dataMovimentacao!: string;
  quantidadeMovimentada!: number;
}