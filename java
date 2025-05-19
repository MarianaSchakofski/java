function toggleSidebar() {
  const sidebar = document.getElementById('sidebar'); // Obtém o elemento com id 'sidebar'
  sidebar.classList.toggle('active'); // Alterna a classe 'active' (mostra/oculta o menu lateral)
}
function mostrarSecao(secaoId) {
  const secoes = document.querySelectorAll('.main section'); // Seleciona todas as <section> dentro de .main
  secoes.forEach(secao => secao.style.display = 'none'); // Esconde todas as seções
  document.getElementById(secaoId).style.display = 'block'; // Mostra a seção com o ID passado
}
function mostrarFormularioProduto() {
  document.getElementById('formProduto').style.display = 'block'; // Exibe o formulário do produto
}

function fecharFormularioProduto() {
  document.getElementById('formProduto').style.display = 'none'; // Oculta o formulário do produto
}
const vendasData = [500, 700, ...]; // Array com valores fictícios de vendas
const meses = ['Jan', 'Feb', ...]; // Meses correspondentes aos dados

const ctx = document.getElementById('graficoVendas').getContext('2d'); // Obtém o contexto 2D do canvas
new Chart(ctx, { // Cria um novo gráfico
  type: 'line', // Tipo de gráfico: linha
  data: {
    labels: meses, // Rótulos do eixo X
    datasets: [{
      label: 'Vendas por Mês (R$)', // Legenda do gráfico
      data: vendasData, // Dados a serem exibidos
      borderColor: '#333', // Cor da linha
      fill: false, // Não preencher abaixo da linha
    }]
  }
});
function excluirProduto(button) {
  const row = button.closest('tr'); // Encontra a linha da tabela mais próxima
  row.remove(); // Remove a linha da tabela
}
function editarproduto(botao) {
  const linha = botao.closest('tr'); // Pega a linha da tabela
  const celulas = linha.cells; // Obtém todas as células da linha

  if (botao.textContent === 'Salvar') return; // Se já estiver em modo salvar, não faz nada

  for (let i = 0; i < celulas.length - 1; i++) {
    celulas[i].innerHTML = `<input value="${celulas[i].textContent}">`; // Torna cada célula editável
  }

  botao.textContent = 'Salvar'; // Altera texto do botão
  botao.onclick = function() { salvarEdicao(this); }; // Define nova ação do botão
}
function salvarEdicao(botao) {
  const linha = botao.closest('tr'); // Pega a linha da tabela
  const celulas = linha.cells;

  for (let i = 0; i < celulas.length - 1; i++) {
    celulas[i].textContent = celulas[i].querySelector('input').value; // Salva o novo valor
  }

  botao.textContent = 'Editar'; // Restaura o botão
  botao.onclick = function() { editarproduto(this); }; // Restaura função de edição
}
function buscarProduto() {
  const filter = document.getElementById('buscaProduto').value.toLowerCase(); // Texto da busca
  const rows = document.querySelectorAll('#tabelaEstoque tr'); // Todas as linhas da tabela

  rows.forEach(row => {
    const td = row.getElementsByTagName('td')[0]; // Primeira célula da linha
    if (td) {
      const text = td.textContent || td.innerText;
      row.style.display = text.toLowerCase().indexOf(filter) > -1 ? '' : 'none'; // Mostra ou esconde a linha
    }
  });
}
function adicionarProduto() {
  const nome = document.getElementById('nomeProduto').value;
  const quantidade = document.getElementById('quantidadeProduto').value;
  const preco = document.getElementById('precoProduto').value;

  if (nome && quantidade && preco) {
    const tabela = document.getElementById('tabelaEstoque');
    const novaLinha = tabela.insertRow(); // Insere nova linha

    novaLinha.innerHTML = `
      <td>${nome}</td>
      <td>${quantidade}</td>
      <td>${preco}</td>
      <td><button onclick="excluirProduto(this)">Excluir</button><button onclick="editarproduto(this)">Editar</button></td>
    `;

    // Limpa os campos
    document.getElementById('nomeProduto').value = '';
    document.getElementById('quantidadeProduto').value = '';
    document.getElementById('precoProduto').value = '';

    fecharFormularioProduto(); // Fecha o formulário
  }
}
function adicionarLancamento() {
  const tipo = document.getElementById('tipoLancamento').value;
  const descricao = document.getElementById('descricaoLancamento').value;
  const valor = parseFloat(document.getElementById('valorLancamento').value);

  if (tipo && descricao && !isNaN(valor)) {
    const lista = document.getElementById('listaLancamentos');
    const li = document.createElement('li');
    li.textContent = `${tipo === 'receita' ? 'Receita' : 'Despesa'} - ${descricao}: R$ ${valor.toFixed(2)}`;
    lista.appendChild(li);

    // Atualiza totais
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

    document.getElementById('saldoFinal').textContent = (receitas - despesas).toFixed(2); // Atualiza saldo
  }
}
document.getElementById("cpf").addEventListener("input", function(e) {
  let value = e.target.value.replace(/\D/g, ""); // Remove não-dígitos
  if (value.length > 11) value = value.slice(0, 11);
  value = value.replace(/(\d{3})(\d)/, "$1.$2");
  value = value.replace(/(\d{3})\.(\d{3})(\d)/, "$1.$2.$3");
  value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3-$4");
  e.target.value = value;
});
document.getElementById('telefone').addEventListener('input', function(e) {
  let value = e.target.value.replace(/\D/g, '');
  if (value.length > 0) value = '(' + value;
  if (value.length > 3) value = value.substring(0, 3) + ') ' + value.substring(3);
  if (value.length > 10) value = value.substring(0, 10) + '-' + value.substring(10);
  if (value.length > 15) value = value.substring(0, 15);
  e.target.value = value;
});
const cepInput = document.getElementById('cep');

cepInput.addEventListener('input', function(e) {
  let value = e.target.value.replace(/\D/g, '');
  if (value.length > 5) {
    value = value.substring(0, 5) + '-' + value.substring(5, 8); // Formata como XXXXX-XXX
  }
  e.target.value = value;
});

cepInput.addEventListener('blur', async function(e) {
  const cep = e.target.value.replace(/\D/g, '');

  if (cep.length === 8) {
    try {
      const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
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
let ultimoId = 0;

function gerarNovoId() {
  ultimoId += 1;
  return ultimoId;
}

document.getElementById('cadfunc').addEventListener('click', function() {
  const novoId = gerarNovoId();
  document.getElementById('resultado').innerHTML = `<p>ID: <strong>${novoId}</strong></p>`;
});
function mostrarfuncionarionovo() {
  document.getElementById('formfunc').style.display = 'block'; // Exibe formulário de funcionário
}

function demitir() {
  document.getElementById('formfunc').style.display = 'none'; // Oculta formulário
}
document.getElementById('telefone1').addEventListener('input', function(e) {
  let value = e.target.value.replace(/\D/g, '');
  if (value.length > 0) value = '(' + value;
  if (value.length > 3) value = value.substring(0, 3) + ') ' + value.substring(3);
  if (value.length > 10) value = value.substring(0, 10) + '-' + value.substring(10);
  if (value.length > 15) value = value.substring(0, 15);
  e.target.value = value;
});

document.getElementById("cpf1").addEventListener("input", function(e) {
  let value = e.target.value.replace(/\D/g, "");
  if (value.length > 11) value = value.slice(0, 11);
  value = value.replace(/(\d{3})(\d)/, "$1.$2");
  value = value.replace(/(\d{3})\.(\d{3})(\d)/, "$1.$2.$3");
  value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3-$4");
  e.target.value = value;
});
