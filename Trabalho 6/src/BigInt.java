public class BigInt {

	//Armazena os digitos
	int[] digitos;

	//Tamanho do numero
	int tamanho;

	public BigInt(int n) {
		if(n == 0) {
			tamanho = 1;
			digitos[0] = 0;
		} else {
			int numeroDeDigitos = 0;
			int guardaN = n;

			//conta a quantidade de digitos
			while(n > 0) {
				numeroDeDigitos++;
				n /= 10;
			}
			//define o tamanho
			tamanho = numeroDeDigitos;
			//alloca espaço
			digitos = new int[tamanho];

			//O digito menos significativo fica armazenado no índice 0.
			for (int i=0; i<tamanho; i++) {
				digitos[i] = guardaN % 10;
				guardaN /= 10;
			}
		}
	}

	public BigInt(String n) {

		tamanho = n.length();
		digitos = new int[tamanho];
		for (int i=0; i<tamanho; i++) {
			digitos[i] = (int)(n.charAt(tamanho-1-i) - '0');
		}
	}

	public int max(int a, int b) {
		if(a > b) {
			return a;
		} else {
			return b;
		}
	}

	public BigInt add(BigInt other) {
		//Cria espaço para a resposta
		int newsize = max(tamanho, other.tamanho) + 1;
		int [] tempdigitos = new int[newsize];

		int carry = 0;

		//loop por todos os numeros
		for (int i=0; i<newsize-1; i++) {
			int somaTemporaria = 0;

			if (i < tamanho)
				somaTemporaria += digitos[i];

			if (i < other.tamanho)
				somaTemporaria += other.digitos[i];

			//Add carry da ultima interação
			somaTemporaria += carry;

			//Pega o resultado e o carry
			tempdigitos[i] = somaTemporaria % 10;
			carry = somaTemporaria/10;
		}

		if (carry == 1)
			tempdigitos[newsize-1] = 1;
		else
			tempdigitos[newsize-1] = 0;

		return new BigInt(tempdigitos);
	}

	public BigInt(int[] bgi) {
		if(zero(bgi)) {
			tamanho = 1;
			digitos[0] = 0;
		} else {
			tamanho = bgi.length;
			while(bgi[tamanho-1] == 0) {
				tamanho--;
			}
			digitos = new int[tamanho];
			for(int i=0; i<tamanho; i++)
				digitos[i] = bgi[i];
		}
	}

	public boolean zero(int[] numeros) {
		for(int i=0; i<numeros.length; i++) {
			if(numeros[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		String numeroString = new String();
		for(int i=0; i<tamanho; i++)
			numeroString = charToString((char)(digitos[i]+'0')) + numeroString;
		return numeroString;
	}

	public String charToString(char c) {
		char[] temp = new char[1];
		temp[0] = c;
		return new String(temp);
	}
}
