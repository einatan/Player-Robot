package PR_BancodeDados;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

class MysqlDAO {
	
	private URL urlConexao;
	public MysqlDAO(URL url) { this.urlConexao = url;}	
	
	public String Executa(HashMap<String, Object> parametros) throws Exception {
		try {
			//Preparar Parametros POST
			StringBuilder postData = new StringBuilder();
	        for(HashMap.Entry<String,Object> param : parametros.entrySet()){
	            if(postData.length() >= 0){
	                postData.append('&');
	                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	                postData.append('=');
	                postData.append(URLEncoder.encode(String.valueOf(param.getValue().toString()),"UTF-8"));
	            }
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        
	        System.setProperty ("https.protocols", "TLSv1.1");
	        HttpURLConnection conn = (HttpURLConnection) urlConexao.openConnection();
	        conn.getConnectTimeout();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	        conn.setDoOutput(true);
	        conn.setDoInput(true);

	        conn.getOutputStream().write(postDataBytes);
	        
	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        for(int c; (c = in.read()) >= 0;){
	            sb.append((char) c);
	        }
	        
	        return sb.toString();
	        
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
}
