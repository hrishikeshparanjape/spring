package com.rishi.app.thirdparty.mandrill.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class Message {
	private Headers headers;

	private To[] to;

	@JsonProperty("track_clicks")
	private String trackClicks;

	@JsonProperty("merge_vars")
	private MergeVars[] mergeVars;

	private String subject;

	@JsonProperty("track_opens")
	private String trackOpens;

	private String merge;

	@JsonProperty("from_name")
	private String fromName;

	@JsonProperty("from_email")
	private String fromEmail;

	@JsonProperty("google_analytics_domains")
	private String[] googleAnalyticsDomains;

	@JsonProperty("view_content_link")
	private String viewContentLink;

	private Attachments[] attachments;

	@JsonProperty("signing_domain")
	private String signingDomain;

	private Metadata metadata;

	private String[] tags;

	@JsonProperty("tracking_domain")
	private String trackingDomain;

	@JsonProperty("auto_text")
	private String autoText;

	private String text;

	private String important;

	private String subaccount;

	@JsonProperty("inline_css")
	private String inlineCss;

	@JsonProperty("bcc_address")
	private String bccAddress;

	@JsonProperty("merge_language")
	private String mergeLanguage;

	@JsonProperty("global_merge_vars")
	private GlobalMergeVars[] globalMergeVars;

	@JsonProperty("url_strip_qs")
	private String urlStripQs;

	@JsonProperty("preserve_recipients")
	private String preserveRecipients;

	@JsonProperty("auto_html")
	private String autoHtml;

	@JsonProperty("return_path_domain")
	private String returnPathDomain;

	private Images[] images;

	@JsonProperty("recipient_metadata")
	private RecipientMetadata[] recipientMetadata;

	private String html;

	@JsonProperty("google_analytics_campaign")
	private String googleAnalyticsCampaign;

	public Headers getHeaders() {
		return headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	public To[] getTo() {
		return to;
	}

	public void setTo(To[] to) {
		this.to = to;
	}

	public String getTrackClicks() {
		return trackClicks;
	}

	public void setTrackClicks(String trackClicks) {
		this.trackClicks = trackClicks;
	}

	public MergeVars[] getMergeVars() {
		return mergeVars;
	}

	public void setMergeVars(MergeVars[] mergeVars) {
		this.mergeVars = mergeVars;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTrackOpens() {
		return trackOpens;
	}

	public void setTrackOpens(String trackOpens) {
		this.trackOpens = trackOpens;
	}

	public String getMerge() {
		return merge;
	}

	public void setMerge(String merge) {
		this.merge = merge;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String[] getGoogleAnalyticsDomains() {
		return googleAnalyticsDomains;
	}

	public void setGoogleAnalyticsDomains(String[] googleAnalyticsDomains) {
		this.googleAnalyticsDomains = googleAnalyticsDomains;
	}

	public String getViewContentLink() {
		return viewContentLink;
	}

	public void setViewContentLink(String viewContentLink) {
		this.viewContentLink = viewContentLink;
	}

	public Attachments[] getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachments[] attachments) {
		this.attachments = attachments;
	}

	public String getSigningDomain() {
		return signingDomain;
	}

	public void setSigningDomain(String signingDomain) {
		this.signingDomain = signingDomain;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getTrackingDomain() {
		return trackingDomain;
	}

	public void setTrackingDomain(String trackingDomain) {
		this.trackingDomain = trackingDomain;
	}

	public String getAutoText() {
		return autoText;
	}

	public void setAutoText(String autoText) {
		this.autoText = autoText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImportant() {
		return important;
	}

	public void setImportant(String important) {
		this.important = important;
	}

	public String getSubaccount() {
		return subaccount;
	}

	public void setSubaccount(String subaccount) {
		this.subaccount = subaccount;
	}

	public String getInlineCss() {
		return inlineCss;
	}

	public void setInlineCss(String inlineCss) {
		this.inlineCss = inlineCss;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getMergeLanguage() {
		return mergeLanguage;
	}

	public void setMergeLanguage(String mergeLanguage) {
		this.mergeLanguage = mergeLanguage;
	}

	public GlobalMergeVars[] getGlobalMergeVars() {
		return globalMergeVars;
	}

	public void setGlobalMergeVars(GlobalMergeVars[] globalMergeVars) {
		this.globalMergeVars = globalMergeVars;
	}

	public String getUrlStripQs() {
		return urlStripQs;
	}

	public void setUrlStripQs(String urlStripQs) {
		this.urlStripQs = urlStripQs;
	}

	public String getPreserveRecipients() {
		return preserveRecipients;
	}

	public void setPreserveRecipients(String preserveRecipients) {
		this.preserveRecipients = preserveRecipients;
	}

	public String getAutoHtml() {
		return autoHtml;
	}

	public void setAutoHtml(String autoHtml) {
		this.autoHtml = autoHtml;
	}

	public String getReturnPathDomain() {
		return returnPathDomain;
	}

	public void setReturnPathDomain(String returnPathDomain) {
		this.returnPathDomain = returnPathDomain;
	}

	public Images[] getImages() {
		return images;
	}

	public void setImages(Images[] images) {
		this.images = images;
	}

	public RecipientMetadata[] getRecipientMetadata() {
		return recipientMetadata;
	}

	public void setRecipientMetadata(RecipientMetadata[] recipientMetadata) {
		this.recipientMetadata = recipientMetadata;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getGoogleAnalyticsCampaign() {
		return googleAnalyticsCampaign;
	}

	public void setGoogleAnalyticsCampaign(String googleAnalyticsCampaign) {
		this.googleAnalyticsCampaign = googleAnalyticsCampaign;
	}
}
