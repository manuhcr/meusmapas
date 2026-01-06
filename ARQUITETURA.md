Arquitetura do Projeto

Este projeto segue uma arquitetura em camadas utilizando Spring Boot, com foco em clareza, separação de responsabilidades e boas práticas de API REST.

---

## 📐 Arquitetura em Camadas

O projeto é dividido nas seguintes camadas:

### Controller
Responsável por:
- Receber requisições HTTP
- Validar dados de entrada (`@Valid`)
- Encaminhar as ações para a camada de serviço
- Retornar respostas em JSON

O Controller **não acessa o banco de dados diretamente**.

---

### Service
Responsável por:
- Conter a lógica de negócio
- Garantir regras e consistência dos dados
- Coordenar operações entre entidades
- Controlar transações com `@Transactional`

Toda regra importante do sistema está concentrada aqui.

---

### Repository
Responsável por:
- Comunicação direta com o banco de dados
- Executar operações CRUD
- Abstrair consultas SQL através do JPA

Utiliza `JpaRepository`.

---

## 🔗 Relacionamento entre Entidades

### Mapa ↔ Ponto

- Um **Mapa** possui vários **Pontos**
- Um **Ponto** pertence a apenas um **Mapa**

Relacionamento:
- `@OneToMany` em `Mapa`
- `@ManyToOne` em `Ponto`

Configurações importantes:
- `cascade = CascadeType.ALL`
- `orphanRemoval = true`

Isso garante que:
- Ao salvar um mapa, seus pontos também são salvos
- Ao remover um ponto do mapa, ele é removido do banco
- Ao deletar um mapa, todos os pontos associados são excluídos

---

## 🔁 Sincronização do Relacionamento

O relacionamento é mantido sincronizado dos dois lados:

- `Ponto.setMapaDoPonto(mapa)`
- `Mapa.addPonto(ponto)`
- `Mapa.removePonto(ponto)`

Isso evita:
- Dados inconsistentes
- Loops infinitos
- Pontos órfãos no banco

---

## 🔄 Uso de @Transactional

Os métodos de escrita utilizam `@Transactional` para garantir que:

- Todas as operações sejam concluídas com sucesso, ou
- Nenhuma alteração seja persistida em caso de erro

Isso evita dados quebrados ou inconsistentes.

---

## 🧭 Atualização de Pontos

A atualização de pontos busca o ponto **diretamente no repositório**, em vez de acessar a lista do mapa.

Motivos:
- Evita problemas de Lazy Loading
- Evita carregar coleções desnecessárias
- Garante atualização direta e segura

---

## 🌐 API REST

A API segue padrões REST:

- `GET` → leitura
- `POST` → criação
- `PUT` → atualização
- `DELETE` → remoção

Endpoints de ponto são aninhados em mapas:
`/mapas/{idMapa}/pontos`


Isso reforça a relação de pertencimento entre as entidades.

---

## ✅ Conclusão

A arquitetura foi pensada para:
- Facilitar manutenção
- Garantir clareza
- Seguir boas práticas de backend
- Permitir evolução futura do projeto