
# Create the virtual env

```bash
../../bin/graalpy-23.1.0-macos-amd64/bin/graalpy -m venv .
```

# Activate

```bash
source ./bin/activate
```

# Install dependencies

```bash
pip install microsoft-kiota-abstractions
pip install microsoft-kiota-http
pip install microsoft-kiota-serialization-json
pip install microsoft-kiota-serialization-text
```

additional dependency (only for GraalVM apparently) verify:
```
pip3 install urllib3
```

# Search the desired API

```bash
kiota search github
```

```bash
kiota download apisguru::github.com -o github.json
kiota generate --language python --openapi github.json -o client --clean-output
```

# Generate the client code

```bash
kiota generate --language python --openapi github.json -o client --clean-output
```

# Run the example code

```bash
python3 main.py
```