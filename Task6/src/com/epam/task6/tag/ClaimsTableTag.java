package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Claim;

public class ClaimsTableTag extends TagSupport{

	private List<Claim> claims;
	
	public void setClaims(List<Claim> claims){
		this.claims = claims;
	}
	
	public List<Claim> getClaims(){
		return claims;
	}
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table border='1'>" );
			for(Claim claim: claims){
				printClaim( claim, out );
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	private void printClaim(Claim claim, JspWriter out) throws IOException{
		out.write( "<tr><td>" );
		out.write( claim.getProblemDescription() );
		out.write( "</td><td>" );
		out.write( String.valueOf( claim.getClaimStatusId()) );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='edit_claim_command'/>"
				+ "<input type='hidden' name='address' value='" + claim
				+ "'/>" + "<input type='submit' value='Edit'/>" + "</form>" );
		out.write( "</td></tr>" );
	}
	
}
