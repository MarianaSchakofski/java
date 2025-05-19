[21:46, 18/05/2025] Mari Mana: <!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>MAGUILA - Sistema de Mercado</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="shortcut icon" type="image/jp" href="imagens/UI.png"/>
  
</head>
<body>

  <header>
    <h1>Sistema de Mercado Maguila</h1>
    <img src="imagens/UI.png" alt="">
    <button class="toggle-btn" onclick="toggleSidebar()">☰ Menu</button>
  </header>
  <div class="container">
    <nav class="sidebar" id="sidebar">
      <h2>Menu</h2>
      <ul>
        <li onclick="mostrarSecao('dashboard')">Dashboard</li>
        <li onclick="mostrarSecao('estoque')">Estoque</li>
        <li onclick="mostrarSecao('financeiro')">Financeiro</li>
        <li onclick="mostrarSecao('despesasReceitas')">Adicionar Receita/Despesa</li>
        <li onclick="mostrarSecao('cadFuncionario')">Cadastrar Funcionário</li>
        <li onclick="mostrarSecao('cadCliente')">Cadastrar Cliente</li>
        <li onclick="mostrarSecao('ultimasVendas')">Últimas Vendas</li>
      </ul>
    </nav>
    <main class="main">

      <!-- DASHBOARD -->
      <section id="dashboard" class="card">
        <h2>Dashboard</h2>
        <p>Comparativo de vendas (últimos 6 meses):</p>
        <canvas id="graficoVendas"></canvas>
      </section>

      <!-- ESTOQUE -->
      <section id="estoque" class="card" style="display:none;">
        <h2>Estoque</h2>
        <input type="text" id="buscaProduto" placeholder="Buscar produto..." onkeyup="buscarProduto()" />
        <button onclick="mostrarFormularioProduto()">Adicionar Produto</button>
        <table>
          <thead>
            <tr><th>Produto</th><th>Quantidade</th><th>Preço (R$)</th><th>Ações</th></tr>
          </thead>
          <tbody id="tabelaEstoque">
            <tr><td>Arroz</td><td>50</td><td>5.99</td><td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td></tr>
            <tr><td>Feijão</td><td>30</td><td>4.79</td><td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td></tr>
            <tr><td>Açúcar</td><td>70</td><td>3.49</td><td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td></tr>
            <tr><td>Óleo</td><td>20</td><td>6.59</td><td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td></tr>
            <tr><td>Sal</td><td>90</td><td>2.10</td><td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td></tr>
            <tr><td>Leite</td><td>45</td><td>4.99</td><td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td></tr>
          </tbody>
        </table>
      </section>

      <!-- FORMULÁRIO DE CADASTRO DE PRODUTO -->
      <section id="formProduto" class="card" style="display:none;">
        <h2>Cadastrar Novo Produto</h2>
        <input type="text" id="nomeProduto" placeholder="Nome do Produto" />
        <input type="number" id="quantidadeProduto" placeholder="Quantidade" />
        <input type="number" id="precoProduto" placeholder="Preço (R$)" />
        <button onclick="adicionarProduto()">Cadastrar Produto</button>
        <button onclick="fecharFormularioProduto()">Cancelar</button>
      </section>

      <!-- FINANCEIRO -->
      <section id="financeiro" class="card" style="display:none;">
        <h2>Financeiro</h2>
        <p><strong>Receitas:</strong> R$ <span id="totalReceitas">0.00</span></p>
        <p><strong>Despesas:</strong> R$ <span id="totalDespesas">0.00</span></p>
        <p><strong>Saldo:</strong> R$ <span id="saldoFinal">0.00</span></p>
      </section>

      <!-- DESPESAS/RECEITAS -->
      <section id="despesasReceitas" class="card" style="display:none;">
        <h2>Adicionar Receita / Despesa</h2>
        <select id="tipoLancamento">
          <option value="receita">Receita</option>
          <option value="despesa">Despesa</option>
        </select>
        <input type="text" id="descricaoLancamento" placeholder="Descrição" />
        <input type="number" id="valorLancamento" placeholder="Valor (R$)" />
        <button onclick="adicionarLancamento()">Adicionar</button>

        <ul id="listaLancamentos"></ul>
      </section>

      <!-- FUNCIONÁRIO -->
      <section id="cadFuncionario" class="card" style="display:none;">
        <h2>Cadastrar Funcionário</h2>
        <input type="text" placeholder="Nome completo" />
        <input type="text" id="cpf" placeholder="CPF" maxlength="14">
        <input type="email" placeholder="Email" />
        <input type="text" placeholder="Cargo" />
        <input type="text" id="cep" placeholder="CEP" maxlength="9" />
        <input type="text" id="logradouro" placeholder="Rua" />
        <input type="text" id="bairro" placeholder="Bairro" />
        <input type="text" id="cidade" placeholder="Cidade" />
        <input type="text" id="uf" placeholder="Estado" maxlength="2" />
        <select name="genero" required>
          <option value="" disabled selected>Gênero</option>
          <option value="M">Masculino (M)</option>
          <option value="F">Feminino (F)</option>
        </select>
        <input type="tel" id="telefone" placeholder="Telefone" maxlength="15">
        <button id="cadfunc" onclick="mostrarfuncionarionovo()">Cadastrar Funcionário</button>
        
      </section>
      <section id="formfunc" class="card" style="display:none;">
        <h2>Novo Funcionário Cadastrado com Sucesso!</h2>
        <div id="resultado"></div>
        <input type="text" id="nomefunc" placeholder="Nome Completo" />
        <input type="number" id="cargofunc" placeholder="Cargo" />
        <input type="number" id="emailfunc" placeholder="Email" />
        <button onclick="demitir()">Manda pro Caraio</button>
      </section>

      <!-- CLIENTE -->
      <section id="cadCliente" class="card" style="display:none;">
        <h2>Cadastrar Cliente</h2>
        <input type="text" placeholder="Nome completo" />
        <input type="email" placeholder="Email" />
        <input type="tel" id="telefone1" placeholder="Telefone" maxlength="15">
        <input type="text" id="cpf1" placeholder="CPF" maxlength="14">
        <input type="text" placeholder="Endereço Completo"/>
        <!-- <textarea placeholder="Endereço completo"></textarea> -->
        <button>Cadastrar Cliente</button>
      </section>

      <!-- ÚLTIMAS VENDAS -->
      <section id="ultimasVendas" class="card" style="display:none;">
        <h2>Últimas Vendas</h2>
        <table>
          <thead>
            <tr><th>Cliente</th><th>Valor</th><th>Produtos</th></tr>
          </thead>
          <tbody>
            <tr><td>Maria Souza…
[21:46, 18/05/2025] Mari Mana: * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Arial', sans-serif;
  }

  body {
    background-color: #f4f4f4;
    color: #333;
  }

  header {
    background-color: #333;
    color: #fff;
    padding: 1rem;
    text-align: center;
  }
  
  header img{
    width: 120px;
    border-right: 30px;
    
  }
  
  .footer {
    display: flex;
    justify-content: space-between; /* Distancia entre as imagens */
    align-items: center; /* Alinha verticalmente */
    height: 80px; /* Altura do footer */
  }

  footer img{
    max-width: 50px; /* Tamanho máximo da imagem */
    height: auto;
  }

  .logoccm{
    margin-left: 100%; 
  }

  .logoestado{
    margin-right: 100%; 
  }

  .container {
    display: flex;
    flex-wrap: wrap;
  }

  .sidebar {
    width: 250px;
    background-color: #2c2c2c;
    color: #fff;
    min-height: 100vh;
    transition: transform 0.3s ease;
  }

  .sidebar h2 {
    text-align: center;
    padding: 1rem 0;
    background-color: #444;
    color: #fff;
  }

  .sidebar ul {
    list-style: none;
  }

  .sidebar ul li {
    padding: 15px 20px;
    border-bottom: 1px solid #555;
    cursor: pointer;
  }

  .sidebar ul li:hover {
    background-color: #444;
  }

  .toggle-btn {
    display: none;
    background-color: #444;
    color: #fff;
    padding: 10px;
    border: none;
    cursor: pointer;
  }

  .main {
    flex: 1;
    padding: 2rem;
  }

  .card {
    background-color: #fff;
    padding: 1rem;
    margin-bottom: 1rem;
    border-left: 5px solid #333;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }

  canvas {
    width: 100% !important;
    max-width: 600px;
    height: auto !important;
  }

  input, select, textarea, button {
    padding: 10px;
    margin: 5px 0;
    width: 100%;
    max-width: 500px;
    border-radius: 5px;
    border: 1px solid #ddd;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 1rem;
  }

  table th, table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: left;
  }

  table th {
    background-color: #444;
    color: #fff;
  }

  table tr:hover {
    background-color: #f1f1f1;
  }

  button {
    background-color: #444;
    color: #fff;
    border: none;
    cursor: pointer;
  }

  button:hover {
    background-color: #555;
  }

  footer {
    text-align: center;
    background-color: #333;
    color: #fff;
    padding: 2rem 1rem;
    margin-top: 2rem;
  }

  footer a {
    color: #fff;
    text-decoration: none;
  }

  @media (max-width: 768px) {
    .sidebar {
      position: absolute;
      transform: translateX(-100%);
      z-index: 999;
    }

    .sidebar.active {
      transform: translateX(0);
    }

    .toggle-btn {
      display: block;
      margin: 1rem;
    }
  }
[21:46, 18/05/2025] Mari Mana: function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('active');
  }

  function mostrarSecao(secaoId) {
    const secoes = document.querySelectorAll('.main section');
    secoes.forEach(secao => secao.style.display = 'none');
    document.getElementById(secaoId).style.display = 'block';
  }

  function mostrarFormularioProduto() {
    document.getElementById('formProduto').style.display = 'block';
  }

  function fecharFormularioProduto() {
    document.getElementById('formProduto').style.display = 'none';
  }

  // Dados fictícios para o gráfico de vendas
  const vendasData = [500, 700, 600, 800, 900, 1100, 700, 600, 800, 900, 1100, 700, 600, 800];
  const meses = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb'];

  // Criando gráfico de vendas
  const ctx = document.getElementById('graficoVendas').getContext('2d');
  new Chart(ctx, {
    type: 'line',
    data: {
      labels: meses,
      datasets: [{
        label: 'Vendas por Mês (R$)',
        data: vendasData,
        borderColor: '#333',
        fill: false,
      }]
    }
  });

  function excluirProduto(button) {
    const row = button.closest('tr');
    row.remove();
  }

  function editarproduto(botao) {
    const linha = botao.closest('tr');
    const celulas = linha.cells;
    
    // Se já está em modo edição, não faz nada
    if (botao.textContent === 'Salvar') return;
    
    // Converte células para inputs
    for (let i = 0; i < celulas.length - 1; i++) {
        celulas[i].innerHTML = <input value="${celulas[i].textContent}">;
    }
    
    // Altera o botão para Salvar
    botao.textContent = 'Salvar';
    botao.onclick = function() { salvarEdicao(this); };
}

function salvarEdicao(botao) {
    const linha = botao.closest('tr');
    const celulas = linha.cells;
    
    // Salva os valores dos inputs
    for (let i = 0; i < celulas.length - 1; i++) {
        celulas[i].textContent = celulas[i].querySelector('input').value;
    }
    
    // Restaura o botão Editar
    botao.textContent = 'Editar';
    botao.onclick = function() { editarProduto(this); };
}

  function buscarProduto() {
    const filter = document.getElementById('buscaProduto').value.toLowerCase();
    const rows = document.querySelectorAll('#tabelaEstoque tr');
    rows.forEach(row => {
      const td = row.getElementsByTagName('td')[0];
      if (td) {
        const text = td.textContent || td.innerText;
        if (text.toLowerCase().indexOf(filter) > -1) {
          row.style.display = '';
        } else {
          row.style.display = 'none';
        }
      }
    });
  }

  function adicionarProduto() {
    const nome = document.getElementById('nomeProduto').value;
    const quantidade = document.getElementById('quantidadeProduto').value;
    const preco = document.getElementById('precoProduto').value;

    if (nome && quantidade && preco) {
      const tabela = document.getElementById('tabelaEstoque');
      const novaLinha = tabela.insertRow();
      novaLinha.innerHTML = `
        <td>${nome}</td>
        <td>${quantidade}</td>
        <td>${preco}</td>
        <td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td>
      `;

      // Limpar campos do formulário
      document.getElementById('nomeProduto').value = '';
      document.getElementById('quantidadeProduto').value = '';
      document.getElementById('precoProduto').value = '';

      // Fechar formulário
      fecharFormularioProduto();
    }
  }

  function adicionarLancamento() {
    const tipo = document.getElementById('tipoLancamento').value;
    const descricao = document.getElementById('descricaoLancamento').value;
    const valor = parseFloat(document.getElementById('valorLancamento').value);
    if (tipo && descricao && !isNaN(valor)) {
      const lista = document.getElementById('listaLancamentos');
      const li = document.createElement('li');
      li.textContent = ${tipo === 'receita' ? 'Receita' : 'Despesa'} - ${descricao}: R$ ${valor.toFixed(2)};
      lista.appendChild(li);

      // Atualizar totais de receitas e despesas
      const totalReceitas = document.getElementById('totalReceitas');
      const totalDespesas = document.getElementById('totalDespesas');
      let receitas = parseFloat(totalReceitas.textContent) || 0;
      let despesas = parseFloat(totalDespesas.textContent) || 0;

      if (tipo === 'receita') {
        receitas += valor;
        totalReceitas.textContent = receitas.toFixed(2);
      } else {
        despesas += valor;
        totalDespesas.textContent = despesas.toFixed(2);
      }

      const saldo = receitas - despesas;
      document.getElementById('saldoFinal').textContent = saldo.toFixed(2);
    }
  }

  document.getElementById("cpf").addEventListener("input", function(e) {
    // Remove tudo que não é dígito
    let value = e.target.value.replace(/\D/g, "");
    
    // Limita a 11 caracteres (CPF tem 11 dígitos)
    if (value.length > 11) value = value.slice(0, 11);
    
    // Adiciona os pontos e hífen DURANTE a digitação
    value = value.replace(/(\d{3})(\d)/, "$1.$2"); // Primeiro ponto
    value = value.replace(/(\d{3})\.(\d{3})(\d)/, "$1.$2.$3"); // Segundo ponto
    value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3-$4"); // Hífen
    
    e.target.value = value;
  });

  document.getElementById('telefone').addEventListener('input', function(e) {
    // Remove tudo que não é número
    let value = e.target.value.replace(/\D/g, '');
    
    // Aplica a máscara
    if (value.length > 0) {
      value = '(' + value;
    }
    if (value.length > 3) {
      value = value.substring(0, 3) + ') ' + value.substring(3);
    }
    if (value.length > 10) {
      value = value.substring(0, 10) + '-' + value.substring(10);
    }
    
    // Limita ao tamanho máximo do formato (99) 99999-9999 (15 caracteres)
    if (value.length > 15) {
      value = value.substring(0, 15);
    }
    
    e.target.value = value;
  });

  const cepInput = document.getElementById('cep');
  
  // Formatação do CEP
  cepInput.addEventListener('input', function(e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length > 5) {
      value = value.substring(0, 5) + '-' + value.substring(5, 8);
    }
    e.target.value = value;
  });

  // Consulta a API quando o CEP estiver completo
  cepInput.addEventListener('blur', async function(e) {
    const cep = e.target.value.replace(/\D/g, '');
    
    if (cep.length === 8) {
      try {
        const response = await fetch(https://viacep.com.br/ws/${cep}/json/);
        const data = await response.json();
        
        if (!data.erro) {
          document.getElementById('logradouro').value = data.logradouro || '';
          document.getElementById('bairro').value = data.bairro || '';
          document.getElementById('cidade').value = data.localidade || '';
          document.getElementById('uf').value = data.uf || '';
        } else {
          alert('CEP não encontrado!');
        }
      } catch (error) {
        console.error('Erro ao buscar CEP:', error);
        alert('Erro ao consultar CEP. Tente novamente.');
      }
    }
  });

  // Variável para armazenar o último ID gerado
  let ultimoId = 0;
        
  // Função para gerar novo ID
  function gerarNovoId() {
      ultimoId += 1; // Incrementa o ID
      return ultimoId;
  }
  
  // Evento de clique do botão
  document.getElementById('cadfunc').addEventListener('click', function() {
      const novoId = gerarNovoId();
      document.getElementById('resultado').innerHTML = `
          <p>ID: <strong>${novoId}</strong></p>
      `;
  });

  function mostrarfuncionarionovo() {
    document.getElementById('formfunc').style.display = 'block';
  }

  function demitir() {
    document.getElementById('formfunc').style.display = 'none';
  }

  document.getElementById('telefone1').addEventListener('input', function(e) {
    // Remove tudo que não é número
    let value = e.target.value.replace(/\D/g, '');
    
    // Aplica a máscara
    if (value.length > 0) {
      value = '(' + value;
    }
    if (value.length > 3) {
      value = value.substring(0, 3) + ') ' + value.substring(3);
    }
    if (value.length > 10) {
      value = value.substring(0, 10) + '-' + value.substring(10);
    }
    
    // Limita ao tamanho máximo do formato (99) 99999-9999 (15 caracteres)
    if (value.length > 15) {
      value = value.substring(0, 15);
    }
    
    e.target.value = value;
  });

  document.getElementById("cpf1").addEventListener("input", function(e) {
    // Remove tudo que não é dígito
    let value = e.target.value.replace(/\D/g, "");
    
    // Limita a 11 caracteres (CPF tem 11 dígitos)
    if (value.length > 11) value = value.slice(0, 11);
    
    // Adiciona os pontos e hífen DURANTE a digitação
    value = value.replace(/(\d{3})(\d)/, "$1.$2"); // Primeiro ponto
    value = value.replace(/(\d{3})\.(\d{3})(\d)/, "$1.$2.$3"); // Segundo ponto
    value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3-$4"); // Hífen
    
    e.target.value = value;
  });
