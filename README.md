# projeto_consistencia
Pojeto de Programação – Key Values Store

###  Definição do Sistema
Sistema distribuído com três servidores que permita armazenar pares chave-valor (também denominado Sistema KV ou Key-Value Store) de forma replicada e consistente, utilizando TCP como protocolo da camada de transporte.

O sistema funcionará de forma similar (porém muito simplificada) ao sistema Zookepeer,
um sistema distribuído que permite a coordenação de servidores.


### Visão geral do sistema
O sistema será composto por 3 servidores (com IPs e portas conhecidas) e muitos clientes. Os clientes poderão realizar requisições em qualquer servidor, tanto para inserir informações key-value no sistema (i.e., PUT) quanto para obtê-las (i.e., GET). Os servidores, por sua vez, deverão atender as requisições dos clientes. Dentre os três servidores, escolhe-se inicialmente um deles como líder, o qual será o único que poderá realizar um PUT. Por outro lado, qualquer servidor poderá responder o GET.
