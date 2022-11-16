package it.equitalia.gdo.svecchiamento.spazio.http;

import it.equitalia.gdo.svecchiamento.spazio.util.ConfigurazioneSpazio;
import it.equitalia.gdo.svecchiamento.spazio.util.ConfigurazioneSpazio.Property;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;

public class SpazioUpload {
	private static Logger logger = Logger.getLogger(SpazioUpload.class);

	public static int doUpload(byte[] contentTXT, String nomeFile) throws Exception {
		logger.debug("Start do Upload");
		
		String user = ConfigurazioneSpazio.getInstance().getProperty(Property.GDO_FLUSSI_SPAZIO_USER);
		
		PostMethod filePost = null;
		int status = 0;

		try {

			logger.debug("*********************************************************\n");
			logger.debug("			SPAZIO HTTP UPLOAD\n");
			logger.debug("*********************************************************\n");

			logger.debug("Recupero il contenuto del file " + "e lo scrivo nel file di appoggio.");

			File file = new File(nomeFile);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(contentTXT);
			logger.debug("File \"" + file + "\" completato correttamente.");

			fos.flush();
			fos.close();

			// Impostazioni dell'ambiente SPAZIO corrispondente messe tutte in
			// un'unica tabella
			logger.debug("Recupero le informazioni dell'ambiente SPAZIO.");

			final Hashtable<String, String> parameters = new Hashtable<String, String>();
			parameters.put("cmd", ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_CMD));
			parameters.put("qm", ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_QM));
			parameters.put("userid", user);
			parameters.put("userpassword", ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_PASS));
			String coda = ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_QUEUE);
			parameters.put("queue", coda);
			parameters.put("sender", user);
			parameters.put("uclass", ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_UCLASS));
			parameters.put("description", nomeFile);
			//parameters.put("corrid", userSpazioVO.getTipoMinuta());
			parameters.put("coding", ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_CODING));

			logger.debug("*********************************************************\n");
			logger.debug("cmd: " + ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_CMD));
			logger.debug("qm: " + ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_QM));
			logger.debug("userid: " +user);
			logger.debug("userpassword: " + ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_PASS));
			logger.debug("queue: " + coda);
			logger.debug("sender: " + user);
			logger.debug("uclass: " + ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_UCLASS));
			logger.debug("description: " + nomeFile);
			//logger.debug("corrid: " + userSpazioVO.getTipoMinuta());
			logger.debug("coding: " + ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_CODING));
			logger.debug("*********************************************************\n");

			URL url = new URL(ConfigurazioneSpazio.getInstance().getProperty(Property.SPAZIO_HTTPACTION));

			HttpClient client = new HttpClient();

			logger.debug("Preparazione HTTP Post su: " + url.getProtocol() + "://" + url.getHost() + url.getPath()
					+ buildParametersString(parameters));

			filePost = new PostMethod(url.getProtocol() + "://" + url.getHost() + url.getPath()
					+ buildParametersString(parameters));

			Part[] parts = { new StringPart("param_name", "value"), new FilePart(file.getName(), file) };

			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));

			logger.debug("Eseguo la richiesta.");
			status = client.executeMethod(filePost);
			logger.info("******** Termine HTTP UPLOAD - Stato della response, codice: " + status +" ********");

			file.delete();

		} catch (Exception e) {
			logger.error("Eccezione in SpazioUpload per invio ad HOST:", e);

		} finally {
			if (filePost != null)
				filePost.releaseConnection();
		}

		logger.debug("End");
		return status;
	}

	// Crea la query string da passare nella URL
	private static String buildParametersString(Hashtable<String, String> params) {
		String ret = "";
		for (Enumeration<String> enumer = params.keys(); enumer.hasMoreElements();) {
			String key = (String) enumer.nextElement();
			String value = (String) params.get(key);
			ret += "&" + key + "=" + value;
		}
		if (!ret.equals(""))
			ret = "?" + ret.substring(1, ret.length());

		return ret;
	}

//	// Metodo che effettua il logon su LDAP per recuperare i parametri per spazio
//	private static UserSpazioVO checkUser(UserSpazioVO userSpazioVO) throws Exception {
//		LDAPUtenti ldapUtenti = new LDAPUtenti();
//		userSpazioVO = ldapUtenti.fillUserAttributes(userSpazioVO);
//		return userSpazioVO;
//	}

}
