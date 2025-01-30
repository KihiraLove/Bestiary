import json
import requests

def extracted_titles(query : str):
    print(query)
    return query


if __name__ == "__main__":
	url = "https://oldschool.runescape.wiki/api.php?"


	request = {'list': 'categorymembers',
				'cmtitle': 'Category:Monsters',
				'action': 'query',
				'format': 'json',
				'cmlimit': '500'}

	custom_agent = {
		'User-Agent': "Bestiary-rl-plugin",
		'From': "d.kertesz16@gmail.com"
	}

	last_continue = {}
	titles = []
	while True:
		req = request.copy()
		req.update(last_continue)
		try:
			result = requests.get(url,headers=custom_agent,params=req).json()
		except requests.exceptions.RequestException as e:
			raise SystemExit(">>> ERROR: Get request error. Exiting.") from e
		if 'query' in result:
			titles.append(extracted_titles(result['query']))
			if 'continue' not in result:
				break
		if 'errors' in result:
			print(result['errors'])
			break
		if 'warnings' in result:
			print(result['warnings'])
			break
		last_continue = result['continue']
