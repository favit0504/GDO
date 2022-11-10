package it.equitalia.gdo.web.actions;

import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;



/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class DownloadManualeAction extends AbstractBaseAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DownloadManualeAction.class);
	
	private String filename;
	private InputStream fileInputStream;

	
	public String execute() throws Exception {

		File fileToDownload = new File(GDOConfig.getInstance().getProperty(GDOConfig.MANUALE_UTENTE));
		filename = fileToDownload.getName();
		
		try{
			fileInputStream = new FileInputStream(fileToDownload);
			
		}catch(FileNotFoundException e) {
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MANUALE_NOT_FOUND));
			return ERROR;
			
		} catch (Exception e){
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_GENERICO));
			return ERROR;
		}
		
		
		return SUCCESS;

	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}	

}
