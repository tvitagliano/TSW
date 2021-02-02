function ricerca(str) {
	var dataList = document.getElementById('ricerca-datalist');
	if (str.length == 0) {
		// rimuove elementi (suggerimenti)
		dataList.innerHTML = '';
		return;
	}

	var xmlHttpReq = new XMLHttpRequest();
	xmlHttpReq.responseType = 'json';
	xmlHttpReq.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// rimuove (suggerimenti) 
			dataList.innerHTML = '';

			for ( var i in this.response) {
				// crea un elemento option
				var option = document.createElement('option');
				// setta il valore
				option.value = this.response[i];
				// aggiunge elemento <option> a datalist
				dataList.appendChild(option);
			}
		}
	}
	xmlHttpReq.open("GET", "RicercaAjax?q=" + encodeURIComponent(str), true);
	xmlHttpReq.send();
}