
def pascal(c: Int, r: Int): Int = {
	var myVal:Int = 0;
	if (c == 0 || c == r) {
		myVal = 1;
	}
	else {
		myVal = pascal(c - 1, r - 1) + pascal(c, r - 1);
	}
	return myVal;
}

def balance(chars: List[Char]): Boolean = {
	var open:Int = 0;
	for (char <- chars) {
		if (char == '(') {
			open += 1;
		}
		else if (char == ')') {
			open -= 1;
		}

		if (open < 0) {
			return false;
		}
	}
	return true;
}