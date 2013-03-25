/* client.js */

function getXMLHttpRequestObject() {
    var ref = null;
    if (window.XMLHttpRequest) {
        ref = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // Older IE.
        ref = new ActiveXObject("MSXML2.XMLHTTP.3.0");
    }
    return ref;
}
function loadPage() {
  var xmlhttp = getXMLHttpRequestObject();
  
  /* 
     this defines a pre-defined function callback
     that will be invoked at
     various stages of the XMLHttpRequest
   */
  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState === 1){
      /* open method invoked successfully */
    }
    if (xmlhttp.readyState === 2){
      /* send method invoked and http response headers recieved */
    }
    if (xmlhttp.readyState === 3){
      /* response content begings to load */
    }
    if (xmlhttp.readyState === 4){
      /* reponse content has finished loading */
      document.getElementById('internet').innerHTML = xmlhttp.responseText;
    }
  };
  //blazing-fresh-book.herokuapp.com
  
  var post_data = "posting=1&hello_server=1";
  
  xmlhttp.open('POST', 'http://blazing-freshbooks.herokuapp.com/welcome', true);
  
  // setting headers triggers CORS Preflight
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  
  xmlhttp.setRequestHeader("Content-length", post_data.length);
  xmlhttp.setRequestHeader("Connection", "close");
  

  xmlhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
  
  xmlhttp.send(post_data);  // No data need to send along with the request.

}
function server_request() {
  loadPage();
}