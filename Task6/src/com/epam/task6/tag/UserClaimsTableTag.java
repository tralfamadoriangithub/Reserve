package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Claim;
import com.epam.task6.entity.ClaimStatusValues;
import com.epam.task6.propertylink.ProjectBundle;
import com.epam.task6.propertylink.ProjectString;
import com.epam.task6.tableentity.ClaimTableEntity;

public class UserClaimsTableTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private List<ClaimTableEntity> claims;
	private ResourceBundle bundle;

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_STRING,
				Locale.getDefault() );

		try {
			out.write( "<table>" );
			out.write( "<th>" );
			out.write( bundle.getString( ProjectString.PROBLEM_DESCRIPTION ) );
			out.write( "</th><th>" );
			out.write( bundle.getString( ProjectString.STREET ) );
			out.write( "</th><th>" );
			out.write( bundle.getString( ProjectString.HOUSE ) );
			out.write( "</th><th>" );
			out.write( bundle.getString( ProjectString.BLOCK ) );
			out.write( "</th><th>" );
			out.write( bundle.getString( ProjectString.FLAT ) );
			out.write( "</th><th>" );
			out.write( bundle.getString( ProjectString.STATUS ) );
			out.write( "</th>" );
			for ( ClaimTableEntity claim : claims ) {
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

	protected void printClaim( ClaimTableEntity claim, JspWriter out )
			throws IOException {
		out.write( "<td>" );
		out.write( claim.getProblemDescription() );
		out.write( "</td><td>" );
		out.write( claim.getAddress().getStreet() );
		out.write( "</td><td>" );
		out.write( String.valueOf( claim.getAddress().getHouseNumber() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( claim.getAddress().getBlockNumber() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( claim.getAddress().getFlatNumber() ) );
		out.write( "</td><td>" );
		out.write( claim.getClaimStatus() );
		out.write( "</td><td>" );
		// ////////////////////////////////////////////////////////////////////
		if ( "Excepted".equals( claim.getClaimStatus() ) ) {
			// ////////////////////////////////////////////////////////////////////
			out.write( "<form action='controller' method='post'>" );
			out.write( "<input type='hidden' name='command' value='edit_claim_command'/>" );
			out.write( "<input type='hidden' name='claim_id' value='" );
			out.write( claim.getClaimId() );
			out.write( "'/>" );
			out.write( "<input type='submit' value='" );
			out.write( bundle.getString( ProjectString.EDIT ) );
			out.write( "'/></form>" );
		}
		out.write( "</td>" );
	}

	public void setClaims( List<ClaimTableEntity> claims ) {
		this.claims = claims;
	}

	public void setClaims( ClaimTableEntity claim ) {
		claims.add( claim );
	}

	public List<ClaimTableEntity> getClaims() {
		return claims;
	}

	public ClaimTableEntity getClaims( int index ) {
		return claims.get( index );
	}

}
