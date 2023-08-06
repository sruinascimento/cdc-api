# Api Casa Do Código (CDC)

## Autor

### Requisitos
É necessário cadastrar um novo autor no sistema. 
Todo autor tem um nome, email e uma descrição. Também queremos saber o instante exato que ele foi registrado.

### Restrições
- O instante não pode ser nulo
- O email é obrigatório
- O email tem que ter formato válido
- O nome é obrigatório
- A descrição é obrigatória e não pode passar de 400 caracteres

### Resultado esperado
- Um novo autor criado e status 200 retornado

## Categoria

### Requisitos
Toda categoria precisa de um nome

### Restrições
- O nome é obrigatório
- O nome não pode ser duplicado

### Resultado esperado
- Uma nova categoria cadastrada no sistema e status 200 retorno
- Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação

