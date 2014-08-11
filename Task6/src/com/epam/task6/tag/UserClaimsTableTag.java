package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Claim;
import com.epam.task6.tableentity.ClaimTableEntity;

public class UserClaimsTableTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	private List<ClaimTableEntity> claims;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table>" );
			out.write( "<th>Problem</th><th>Street</th><th>House</th><th>Block</th><th>Flat</th><th>Status</th>" );
			for(ClaimTableEntity claim: claims){
				out.write( "<tr>" );
				printClaim( claim, out );
				out.write( "</tr>" );
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	protected void printClaim(ClaimTableEntity claim, JspWriter out) throws IOException{
		out.write( "<td>" );
		out.write( claim.getProblemDescription() );
		out.write( "</td><td>" );
		out.write( claim.getAddress().getStreet() );
		out.write( "</td><td>" );
		out.write( String.valueOf( claim.getAddress().getHouseNumber()) );
		out.write( "</td><td>" );
		out.write( String.valueOf( claim.getAddress().getBlockNumber()) );
		out.write( "</td><td>" );
		out.write( String.valueOf( claim.getAddress().getFlatNumber()) );
		out.write( "</td><td>" );
		out.write( claim.getClaimStatus() );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='edit_claim_command'/>"
				+ "<input type='hidden' name='claim_id' value='" + claim.getClaimId()
				+ "'/>" + "<input type='submit' value='Edit'/>" + "</form>" );
		out.write( "</td>" );
	}
	
	public void setClaims(List<ClaimTableEntity> claims){
		this.claims = claims;
	}
	
	public void setClaims( ClaimTableEntity claim ){
		claims.add( claim );
	}
	
	public List<ClaimTableEntity> getClaims(){
		return claims;
	}
	
	public ClaimTableEntity getClaims(int index){
		return claims.get( index );
	}
	
}
